package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CarImage;

public interface CarImageDao extends JpaRepository<CarImage, Integer> {
	
	int countByCar_CarId(int carId);
	
	List<CarImage> getByCar_CarId(int carId);
	
	
	
}
