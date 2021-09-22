package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.ApplicationUser;

public interface ApplicationUserDao extends JpaRepository<ApplicationUser, Integer>{

	ApplicationUser getByEmail(String email);
	
	boolean existsByEmail(String email);
}
