package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarDamageInformationService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CarDamageInformation;
import com.etiya.ReCapProject.entities.requests.create.CreateCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarDamageInformationRequest;

@RestController
@RequestMapping("api/cardamageinformations")
public class CarDamageInformationsController {
	
	private CarDamageInformationService carDamageInformationService;

	public CarDamageInformationsController(CarDamageInformationService carDamageInformationService) {
		super();
		this.carDamageInformationService = carDamageInformationService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarDamageInformation>> getAll() {
		return this.carDamageInformationService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CarDamageInformation> getById(int carDamageInformationId) {
		return this.carDamageInformationService.getById(carDamageInformationId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarDamageInformationRequest createCarDamageInformationRequest) {
		return this.carDamageInformationService.add(createCarDamageInformationRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarDamageInformationRequest updateCarDamageInformationRequest) {
		return this.carDamageInformationService.update(updateCarDamageInformationRequest);
	}
	
	@PostMapping("/delete")
	public Result delte(DeleteCarDamageInformationRequest deleteCarDamageInformationRequest) {
		return this.carDamageInformationService.delete(deleteCarDamageInformationRequest);
	}
	
	@GetMapping("/getCarDmageInformationsByCarId")
	public DataResult<List<CarDamageInformation>> getCarDmageInformationsByCarId(int carId){
		return this.carDamageInformationService.getCarDmageInformationsByCarId(carId);
	}

}
