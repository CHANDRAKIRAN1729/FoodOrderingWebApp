package com.foodapp.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.app.dto.Admin;
import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.dto.LoginDetails;
import com.foodapp.app.dto.Staff;
import com.foodapp.app.exception.WrongCredentialsException;
import com.foodapp.app.repository.AdminRepository;
import com.foodapp.app.repository.BranchManagerRepository;
import com.foodapp.app.repository.StaffRepository;
import com.foodapp.app.util.AES;
import com.foodapp.app.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	BranchManagerRepository branchManagerRepository;
	@Autowired
	StaffRepository staffRepository;
	@Autowired
	LoginDetails details;
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<LoginDetails>> loginAdmin(@RequestBody Admin admin2){
		ResponseStructure<LoginDetails> structure = new ResponseStructure<>();
		String email=admin2.getEmail();
		String password=admin2.getPassword();
		String pass=AES.encrypt(password);
		
			List<Admin> admin=adminRepository.findByEmail(email);
//			if(admin.size()>0) {
//			System.out.println(admin.get(0));}
				if(admin.size()>0 && admin.get(0).getPassword().equals(pass)) {
					System.out.println("Inside Admin");
					details.setRole("admin");
					details.setId(admin.get(0).getId());
					details.setEmail(email);
					details.setName(admin.get(0).getName());
					structure.setMessage("Admin Login Successful");
		            structure.setStatus(HttpStatus.OK.value());
		            structure.setData(details);
		            return new ResponseEntity<>(structure, HttpStatus.OK);
				}
			List<BranchManager> branchManagers=branchManagerRepository.findByEmail(email);
			 if(branchManagers.size()>0 && branchManagers.get(0).getPassword().equals(pass)) {
					System.out.println("Inside BranchManager");
				details.setRole("manager");
				details.setId(branchManagers.get(0).getId());
				details.setEmail(email);
				details.setName(branchManagers.get(0).getName());
				structure.setMessage("BranchManger Login Successful");
	            structure.setStatus(HttpStatus.OK.value());
	            structure.setData(details);
	            return new ResponseEntity<>(structure, HttpStatus.OK);
			}
			List<Staff> staffs=staffRepository.findByEmail(email);
			 if(staffs.size()>0 && staffs.get(0).getPassword().equals(pass)){
					System.out.println("Inside Staff");
				details.setRole("staff");
				details.setId(staffs.get(0).getId());
				details.setEmail(email);
				details.setName(staffs.get(0).getName());
				structure.setMessage("Staff Login Successful");
	            structure.setStatus(HttpStatus.OK.value());
	            structure.setData(details);
	            return new ResponseEntity<>(structure, HttpStatus.OK);
			}
			 else {
				 throw new WrongCredentialsException();
			 }
//		throw new WrongCredentialsException();
		
		
	}

}
