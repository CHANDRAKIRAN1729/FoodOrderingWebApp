package com.foodapp.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapp.app.dto.Admin;
import com.foodapp.app.repository.AdminRepository;

@Repository
public class AdminDao {
	
	@Autowired
	AdminRepository adminRepository;
	
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Optional<Admin> findAdminById(int id){
		return adminRepository.findById(id);
	}
	
	public List<Admin> findAllAdmins(){
		return adminRepository.findAll();
	}
	
	public Admin updateAdmin(Admin admin,int id) {
		Optional<Admin> optional=findAdminById(id);
		Admin admin2=optional.get();
		admin2.setEmail(admin.getEmail());
		admin2.setName(admin.getName());
		admin2.setPassword(admin.getPassword());
		admin2.setPhoneNumber(admin.getPhoneNumber());
		adminRepository.save(admin2);
		return admin2;
	}
	
	public Optional<Admin> deleteAdmin(int id){
		Optional<Admin> optional=findAdminById(id);
		if(optional.isEmpty()) {
			return null;
		}
		else
		{
			adminRepository.delete(optional.get());
			return optional;
		}
	}
	
//	public Admin loginAdmin(Admin admin){
//		List<Admin> items= adminRepository.findByEmail(admin.getEmail());
//		for(Admin a:items) {
//		System.out.println(a.getEmail());
//		System.out.println(a.getPassword());
//		System.out.println(admin.getEmail());
//		System.out.println(admin.getPassword());
//			if(a.getEmail().equals(admin.getEmail())) {
//				if(a.getPassword().equals(admin.getPassword()))
//					return a;
//			}
//		}
//		return null;
//	}
}
