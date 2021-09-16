package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;


public interface CarDao extends JpaRepository<Car, Integer>{
	
	@Query("Select new com.etiya.ReCapProject.entities.dtos.CarDetailDto"
			+ " (c.carName, b.brandName, col.colorName , c.dailyPrice) "
			+ " From Brand b Inner Join b.cars c"
			+ " Inner Join c.color col")
	List<CarDetailDto> getCarWithBrandAndColorDetails();
	
	
}
