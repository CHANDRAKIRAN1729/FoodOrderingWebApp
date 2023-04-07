package com.foodapp.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.foodapp.app.dto.BranchManager;
import com.foodapp.app.repository.BranchManagerRepository;

@Repository
public class BranchManagerDao {

	@Autowired
	BranchManagerRepository branchManagerRepository;
	
	public BranchManager saveBranchManager(BranchManager branchManager) {
		return branchManagerRepository.save(branchManager);
	}
	
	public Optional<BranchManager> findBranchManagerById(int id){
		return branchManagerRepository.findById(id);
	}
	
	public List<BranchManager> findAllBranchManagers(){
		return branchManagerRepository.findAll();
	}
	
	public BranchManager updateBranchManager(BranchManager branchManager,int id) {
		Optional<BranchManager> optional=findBranchManagerById(id);
		BranchManager branchManager2=optional.get();
		branchManager2.setName(branchManager.getName());
		branchManager2.setEmail(branchManager.getEmail());
		branchManager2.setAdmin(branchManager2.getAdmin());
		branchManager2.setRole(branchManager2.getRole());
		branchManager2.setStaffs(branchManager2.getStaffs());
		branchManager2.setPassword(branchManager.getPassword());
		branchManager2.setPhoneNumber(branchManager.getPhoneNumber());
		branchManagerRepository.save(branchManager2);
		return branchManager2;
	}
	
	public Optional<BranchManager> deleteBranchManager(int id){
		Optional<BranchManager> optional=findBranchManagerById(id);
		if(optional.isEmpty()) {
			return null;
		}
		else
		{
			branchManagerRepository.delete(optional.get());
			return optional;
		}
	}
}
