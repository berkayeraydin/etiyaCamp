package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;


public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
	
	boolean existsByApplicationUser_UserId(int applicationUserId);
	
}
