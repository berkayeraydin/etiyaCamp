package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.RentalAdditional;

public interface RentalAdditionalDao extends JpaRepository<RentalAdditional, Integer>{
	
	boolean existsByRentalAdditionalName(String rentalAdditionalName);
}
