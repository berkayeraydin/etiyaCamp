package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarRequest;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	
	private CarService carService;
	
	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	} 
	
	@GetMapping("/getAll")
	public DataResult<List<Car>> getAll(){
		
		return this.carService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Car> getById( int carId){
		
		return this.carService.getById(carId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarRequest createCarRequest){
		
		return  this.carService.add(createCarRequest);
		
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarRequest updateCarRequest){
		
		return this.carService.update(updateCarRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(int carId){
		
		return  this.carService.delete(carId);
	}
	
	@GetMapping("/getCarWithBrandAndColorDetails")
	public DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails(){
		
		return this.carService.getCarWithBrandAndColorDetails();
	}
	
	@PostMapping("/getCarImagesByCarId")
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId){
		
		return this.carService.getCarImagesByCarId(carId);
	}
	
	@GetMapping("/getByBrandName")
	public DataResult<List<Car>> getByBrandName(String brandName){
		
		return this.carService.getByBrandName(brandName);
	}
	
	@GetMapping("/getByColorName")
	public DataResult<List<Car>> getByColorName(String colorName){
		
		return this.carService.getByColorName(colorName);
	}
	
}
