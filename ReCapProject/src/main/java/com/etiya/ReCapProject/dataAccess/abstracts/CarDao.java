package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {

//	@Query("Select new com.etiya.ReCapProject.entities.dtos.CarDetailDto"
//	+ " (c.carName, b.brandName , col.colorName, c.dailyPrice)" 
//	+ " From Car c Inner Join c.brand b"
//	+ " Inner Join c.color col")
//List<CarDetailDto> getCarsWithBrandAndColorDetail();
//
//@Query("Select new com.etiya.ReCapProject.entities.dtos.CarDetailDto"
//	+ " (c.carName, b.brandName , col.colorName, c.dailyPrice)" 
//	+ " From Car c Inner Join c.brand b"
//	+ " Inner Join c.color col" 
//	+ " Where c.carId=:carId")
//CarDetailDto getCarWithBrandAndColorDetail(int carId);

	List<Car> getByColor_ColorIdAndIsListedIsTrue(int colorId);

	List<Car> getByBrand_BrandIdAndIsListedIsTrue(int brandId);
	
	List<Car> getByCity_CityIdAndIsListedIsTrue(int cityId);

	List<Car> getByIsListedIsTrue();

}
