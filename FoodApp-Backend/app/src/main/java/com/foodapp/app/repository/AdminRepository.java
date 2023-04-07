package com.foodapp.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodapp.app.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public List<Admin> findByEmail(String email);
}
