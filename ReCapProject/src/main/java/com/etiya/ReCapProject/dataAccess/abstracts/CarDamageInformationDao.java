package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.CarDamageInformation;

public interface CarDamageInformationDao extends JpaRepository<CarDamageInformation, Integer>{
	
	List<CarDamageInformation> getByCar_CarId(int carId);
}
