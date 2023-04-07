package com.foodapp.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.app.dao.AdminDao;
import com.foodapp.app.dto.Admin;
import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.dto.Staff;
import com.foodapp.app.service.AdminService;
import com.foodapp.app.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/saveadmin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return adminService.saveAdmin(admin);
	}
	
	@GetMapping("/getadmin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> getAdmin(@PathVariable int id) {
		return adminService.getAdminById(id);
	}
	
	@GetMapping("/getadmins")
	public ResponseEntity<ResponseStructure<List<Admin>>> getAdmins(){
		return adminService.getAllAdmins();
	}
	
	@PutMapping("/updateadmin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin,@PathVariable int id) {
		return adminService.updateAdmin(admin, id);
	}
	
	@DeleteMapping("/deleteadmin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@PathVariable  int id) {
		return adminService.deleteAdmin(id);
	}
	
//	@PostMapping("/loginadmin")
//	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(@RequestBody Admin admin){
//		return adminService.loginAdmin(admin);
//	}
	@GetMapping("getmanagerbyid/{id}")
	public ResponseEntity<ResponseStructure<List<BranchManager>>> getStaffById(@PathVariable int id){
		return adminService.getmanagerById(id);
	}
}
