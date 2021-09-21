package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;


public interface CarDao extends JpaRepository<Car, Integer>{
	
	@Query("Select new com.etiya.ReCapProject.entities.dtos.CarDetailDto "
			+ " (c.carName, b.brandName, col.colorName , c.dailyPrice ) "
			+ " From Car c Inner Join c.brand b "
			+ " Inner Join c.color col ")
	List<CarDetailDto> getCarsWithBrandAndColorDetails();
	
	
	@Query("select c.carImages from Car c where c.id= :carId")
	List<CarImage> existsCarImagesByCarId(int carId);
	
	
	//boolean existsByCarImagesIsNullAndCarId(int carId);
	
	//boolean existsByCarImagesIsNull();
	
	@Query("From Car where brand.brandId=:brandId ")
	List<Car> getByBrandId(int brandId);
	
	@Query("From Car where color.colorId=:colorId ")
	List<Car> getByColorId(int colorId);
	// List<Car> getByColor_ColorId(int colorId);
	
	
	
	
}
