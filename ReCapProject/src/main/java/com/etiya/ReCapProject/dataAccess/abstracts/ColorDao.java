package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Color;


public interface ColorDao extends JpaRepository<Color, Integer> {
	
	boolean existsByColorName(String colorName);
}
