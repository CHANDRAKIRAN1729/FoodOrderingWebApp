package com.foodapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.foodapp.app.dao.AdminDao;
import com.foodapp.app.dao.BranchManagerDao;
import com.foodapp.app.dto.Admin;
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
public class BranchManagerService {
	
	@Autowired
    BranchManagerDao dao;
	@Autowired
	LoginDetails details;
	@Autowired
	AdminDao adminDao;
    public ResponseEntity<ResponseStructure<BranchManager>> saveBranchManager(BranchManager branchManager,int id) {
        ResponseStructure<BranchManager> structure = new ResponseStructure<>();
        branchManager.setPassword(AES.encrypt(branchManager.getPassword()));
        branchManager.setRole("manager");
        if(branchManager.getEmail()==null)
        {
        	throw new NullPointerException();
        }
        try {
        Admin admin=adminDao.findAdminById(id).get();
        branchManager.setAdmin(admin);
        structure.setMessage("BranchManager Saved Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(dao.saveBranchManager(branchManager));
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
        }
        catch (Exception e) {
			// TODO: handle exception
        	throw new UniqueException();
		}
    }

    public ResponseEntity<ResponseStructure<BranchManager>> deleteBranchManager(int id) {
        Optional<BranchManager> optional = dao.deleteBranchManager(id);
        ResponseStructure<BranchManager> structure = new ResponseStructure<>();
        if (optional == null) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("deleted Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<BranchManager>> getBranchManagerById(int id) {
        Optional<BranchManager> optional = dao.findBranchManagerById(id);
        optional.get().setPassword(AES.decrypt(optional.get().getPassword()));
        ResponseStructure<BranchManager> structure = new ResponseStructure<>();
        if (optional.isEmpty()) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("BranchManager Found");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<BranchManager>>> getAllBranchManagers() {
        ResponseStructure<List<BranchManager>> structure = new ResponseStructure<>();
        List<BranchManager> list = dao.findAllBranchManagers();
        if (list.isEmpty())
            throw new IdNotFoundException();
        else {
            structure.setMessage("fetched Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(list);
            return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<BranchManager>> updateBranchManager(BranchManager branchManager, int id) {
        branchManager.setPassword(AES.encrypt(branchManager.getPassword()));
        BranchManager branchManager2 = dao.updateBranchManager(branchManager, id);
        ResponseStructure<BranchManager> structure = new ResponseStructure<>();
        if (branchManager2 != null) {
            structure.setMessage("Updated Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(branchManager);
            return new ResponseEntity<ResponseStructure<BranchManager>>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
    
     public ResponseEntity<ResponseStructure<List<Staff>>> getstaffById(int id){
    	 BranchManager branchManager=dao.findBranchManagerById(id).get();
    	 List<Staff> staff=branchManager.getStaffs();
    	 ResponseStructure<List<Staff>> structure = new ResponseStructure<>();
             structure.setMessage("Found Successfully");
             structure.setStatus(HttpStatus.OK.value());
             structure.setData(staff);
             return new ResponseEntity<ResponseStructure<List<Staff>>>(structure, HttpStatus.OK);

	}
     
     public ResponseEntity<ResponseStructure<Menu>> getMenuByManager(int id){
    	 BranchManager branchManager=dao.findBranchManagerById(id).get();
    	 ResponseStructure<Menu> structure = new ResponseStructure<>();
             structure.setMessage("Found Successfully");
             structure.setStatus(HttpStatus.OK.value());
             structure.setData(branchManager.getMenu());
             return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);

	}
     
	
}

