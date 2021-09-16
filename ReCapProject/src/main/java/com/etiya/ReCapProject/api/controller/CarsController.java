package com.etiya.ReCapProject.api.controller;

import java.util.List;

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
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;

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
	public DataResult<Car> getById(@RequestBody int carId){
		
		return this.carService.getById(carId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Car car){
		
		return  this.carService.add(car);
		
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody Car car){
		
		return this.carService.update(car);
	}
	
	@PostMapping("/delete")
	public Result delete(Car car){
		
		return  this.carService.delete(car);
	}
	
	@GetMapping("/getCarWithBrandAndColorDetails")
	public DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails(){
		
		return this.carService.getCarWithBrandAndColorDetails();
	}
}
