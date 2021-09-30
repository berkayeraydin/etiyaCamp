package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalDao  extends JpaRepository<Rental, Integer>{
	
	boolean existsByIsCarReturnedIsFalseAndCar_CarId(int carId);
	
	// Rental getRentalByRentalId(int rentalId);
}
