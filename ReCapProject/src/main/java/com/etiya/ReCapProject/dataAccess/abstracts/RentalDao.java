package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.etiya.ReCapProject.entities.concretes.Rental;

public interface RentalDao  extends JpaRepository<Rental, Integer>{
	
	boolean existsByIsCarReturnedIsFalseAndCar_CarId(int carId);
	
	List<Rental> getByApplicationUser_UserId(int applicationUserId);
}
