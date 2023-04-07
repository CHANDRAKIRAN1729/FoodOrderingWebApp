package com.foodapp.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.foodapp.app.dao.AdminDao;
import com.foodapp.app.dto.Admin;
import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.dto.Staff;
import com.foodapp.app.exception.IdNotFoundException;
import com.foodapp.app.exception.NullPointerException;
import com.foodapp.app.exception.UniqueException;
import com.foodapp.app.util.AES;
import com.foodapp.app.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
    AdminDao dao;

    public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
        ResponseStructure<Admin> structure = new ResponseStructure<>();
        admin.setRole("admin");
        admin.setPassword(AES.encrypt(admin.getPassword()));
        if(admin.getEmail()==null) {
        	throw new NullPointerException();
        }
        try {
        structure.setMessage("Admin Saved Successfully");
        structure.setStatus(HttpStatus.CREATED.value());
        structure.setData(dao.saveAdmin(admin));
        return new ResponseEntity<>(structure, HttpStatus.CREATED);
        }
        catch (Exception e) {
			throw new UniqueException();
		}
    }

    public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(int id) {
        Optional<Admin> optional = dao.deleteAdmin(id);
        ResponseStructure<Admin> structure = new ResponseStructure<>();
        if (optional == null) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("deleted Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Admin>> getAdminById(int id) {
        Optional<Admin> optional = dao.findAdminById(id);
        optional.get().setPassword(AES.decrypt(optional.get().getPassword()));
        ResponseStructure<Admin> structure = new ResponseStructure<>();
        if (optional.isEmpty()) {
            throw new IdNotFoundException();
        } else {
            structure.setMessage("Admin Found");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(optional.get());
            return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<List<Admin>>> getAllAdmins() {
        ResponseStructure<List<Admin>> structure = new ResponseStructure<>();
        List<Admin> list = dao.findAllAdmins();
        if (list.isEmpty())
            throw new IdNotFoundException();
        else {
            structure.setMessage("fetched Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(list);
            return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
        }
    }

    public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin, int id) {
        admin.setPassword(AES.encrypt(admin.getPassword()));
        Admin admin2 = dao.updateAdmin(admin, id);
        ResponseStructure<Admin> structure = new ResponseStructure<>();
        if (admin2 != null) {
            structure.setMessage("Updated Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(admin);
            return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
        } else {
            throw new IdNotFoundException();
        }
    }
    
//    public ResponseEntity<ResponseStructure<Admin>> loginAdmin(Admin admin){
//    	ResponseStructure<Admin> structure = new ResponseStructure<>();
//    	admin.setPassword(AES.encrypt(admin.getPassword()));
//    	Admin admin2=dao.loginAdmin(admin);
//    	if(admin2!=null)
//    	{
//    		structure.setMessage("Admin Login Successful");
//            structure.setStatus(HttpStatus.OK.value());
//            structure.setData(admin2);
//            return new ResponseEntity<>(structure, HttpStatus.OK);	
//    	}
//    	else {
//    		throw new WrongCredentialsException();
//    	}
//    }
    
    public ResponseEntity<ResponseStructure<List<BranchManager>>> getmanagerById(int id){
   	 Admin branchManager=dao.findAdminById(id).get();
   	 List<BranchManager> staff=branchManager.getBranchManagers();
   	 ResponseStructure<List<BranchManager>> structure = new ResponseStructure<>();
            structure.setMessage("Found Successfully");
            structure.setStatus(HttpStatus.OK.value());
            structure.setData(staff);
            return new ResponseEntity<ResponseStructure<List<BranchManager>>>(structure, HttpStatus.OK);

	}
	
    
}
