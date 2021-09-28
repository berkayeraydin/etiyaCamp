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
import com.etiya.ReCapProject.core.utilities.result.*;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarRequest;

@RestController
@RequestMapping("api/cars")
public class CarsController {
	CarService carService;

	@Autowired
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Car>> getAll() {
		return this.carService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<Car> getById(int carId) {
		return this.carService.getById(carId);
	}
	
	@GetMapping("/getcarsDetails")
	public DataResult<List<CarDetailDto>> getCarsDetails() {
		return this.carService.getAllCarsDetails();
	}
	
	@GetMapping("/getcarDetails")
	DataResult<CarDetailDto> getCarDetailsByCarId(int carId){
		return this.carService.getCarDetailsByCarId(carId);
	}
	
	@GetMapping("/getcarsbycolor")
	public DataResult<List<Car>> getCarsByColorId(int colorId) {
		return this.carService.getCarsByColorId(colorId);
	}

	@GetMapping("/getcarsbybrand")
	public DataResult<List<Car>> getCarsByBrandId(int brandId) {
		return this.carService.getCarsByBrandId(brandId);
	}
	
	@GetMapping("/getcarsbycity")
	public DataResult<List<Car>> getCarsCitys_CityId(int cityId){
		return this.carService.getCarsCitys_CityId(cityId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	
	
}