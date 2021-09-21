package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalDao  extends JpaRepository<Rental, Integer>{
	
	// List<Rental> getByCar_CarId(int carId);
	
	// false ise true 
	boolean existsByIsCarReturnedIsFalseAndCar_CarId(int carId);
}
