package com.foodapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodapp.app.dao.BranchManagerDao;
import com.foodapp.app.dao.StaffDao;
import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.dto.LoginDetails;
import com.foodapp.app.dto.Menu;
import com.foodapp.app.dto.Staff;
import com.foodapp.app.exception.IdNotFoundException;
import com.foodapp.app.exception.NullPointerException;
import com.foodapp.app.exception.UniqueException;
import com.foodapp.app.util.AES;
import com.foodapp.app.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
    StaffDao dao;

	@Autowired
	BranchManagerDao branchManagerDao;
	@Autowired
	LoginDetails details;
    public ResponseEntity<ResponseStructure<Staff>> saveStaff(Staff staff,int id) {
        ResponseStructure<Staff> structure = new ResponseStructure<>();
        staff.setPassword(AES.encrypt(staff.getPassword()));
        staff.setRole("staff");
        if(staff.getEmail()==null) {
        	throw new NullPointerException();
        }
        try {
        BranchManager branchManager=branchManagerDao.findBranchManagerById(id).get();
        staff.setBranchManager(branchManager);
        structure.setMessage("Staff Saved Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(dao.saveStaff(staff));
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
        }
        catch (Exception e) {
			// TODO: handle exception
        	throw new UniqueException();
		}
    }

    public ResponseEntity<ResponseStructure<Staff>> deleteStaff(int id) {
        Optional<Staff> optional = dao.deleteStaff(id);
        ResponseStructure<Staff> structure = new ResponseStructure<>();
        if (optional == null) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("deleted Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Staff>> getStaffById(int id) {
        Optional<Staff> optional = dao.findStaffById(id);
        optional.get().setPassword(AES.decrypt(optional.get().getPassword()));
        ResponseStructure<Staff> structure = new ResponseStructure<>();
        if (optional.isEmpty()) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("Staff Found");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Staff>>> getAllStaffs() {
        ResponseStructure<List<Staff>> structure = new ResponseStructure<>();
        List<Staff> list = dao.findAllStaffs();
        if (list.isEmpty())
            throw new IdNotFoundException();
        else {
            structure.setMessage("fetched Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(list);
            return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Staff>> updateStaff(Staff staff, int id) {
        staff.setPassword(AES.encrypt(staff.getPassword()));
        Staff staff2 = dao.updateStaff(staff, id);
        ResponseStructure<Staff> structure = new ResponseStructure<>();
        if (staff2 != null) {
            structure.setMessage("Updated Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(staff);
            return new ResponseEntity<ResponseStructure<Staff>>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
    
    public ResponseEntity<ResponseStructure<List<Staff>>> getStaffByManager(int id) {
        if(branchManagerDao.findBranchManagerById(id).isEmpty())
            throw new IdNotFoundException();
        BranchManager manager=branchManagerDao.findBranchManagerById(id).get();
        ResponseStructure<List<Staff>> structure = new ResponseStructure<>();
        structure.setMessage("fetched Successfully");
        structure.setStatus(HttpStatus.OK.value());
        structure.setData(dao.getStaffByManager(manager));
        return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);
    }
    
    public ResponseEntity<ResponseStructure<LoginDetails>> getMenuById(int id){
    	Staff staff=dao.findStaffById(id).get();
    	BranchManager branchManager=staff.getBranchManager();
    	Menu menu=branchManager.getMenu();
    	details.setId(menu.getId());
        ResponseStructure<LoginDetails> structure = new ResponseStructure<>();
        structure.setData(details);
        structure.setMessage("Found Menu");
        structure.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<LoginDetails>>(structure, HttpStatus.OK);

    }
}
