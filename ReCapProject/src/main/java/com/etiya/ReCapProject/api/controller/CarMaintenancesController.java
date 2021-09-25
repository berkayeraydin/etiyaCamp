package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarMaintenanceService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CarMaintenance;
import com.etiya.ReCapProject.entities.requests.create.CreateCarMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarMaintenanceRequest;

@RestController
@RequestMapping("api/carmaintenances")
public class CarMaintenancesController {
	
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("/getAll")
	public DataResult<List<CarMaintenance>> getAll() {
		return this.carMaintenanceService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<CarMaintenance> getById(int rentalId) {
		return this.carMaintenanceService.getById(rentalId);
	}

//	@GetMapping("/getRentalDetailsByRentalId")
//	public DataResult<RentalDetailDto> getRentalDetailsByRentalId(int rentalId) {
//		return this.rentalService.getRentalDetailsByRentalId(rentalId);
//	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@PostMapping("/delete")
	public Result delte(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@PostMapping("/carReturnedIsTrue")
	public Result carReturnedIsTrue(int carMaintenanceId) {
		return this.carMaintenanceService.CarAtMaintenanceReturnedIsTrue(carMaintenanceId);
	}
}
