package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
	
	boolean existsByApplicationUser_UserId(int applicationUserId);
	
	CorporateCustomer getByApplicationUser_UserId(int applicationUserId);
}
