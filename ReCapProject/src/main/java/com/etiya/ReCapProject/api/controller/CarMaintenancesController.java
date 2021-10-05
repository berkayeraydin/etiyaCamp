package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CarMaintenanceService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CarMaintenance;
import com.etiya.ReCapProject.entities.dtos.CarMaintenanceDetailDto;
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
	public DataResult<CarMaintenance> getById(@RequestParam("carMaintenanceId") int carMaintenanceId) {
		return this.carMaintenanceService.getById(carMaintenanceId);
	}

	@GetMapping("/getCarMaintenancesDetail")
	DataResult<List<CarMaintenanceDetailDto>> getCarMaintenancesDetail() {
		return this.carMaintenanceService.getCarMaintenancesDetail();
	}

	@GetMapping("/getCarMaintenanceDetailById")
	DataResult<CarMaintenanceDetailDto> getCarMaintenanceDetailById(int carMaintenanceId) {
		return this.carMaintenanceService.getCarMaintenanceDetailById(carMaintenanceId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}

	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}

	@DeleteMapping("/delete")
	public Result delte(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}

	@PostMapping("/carReturnedIsTrue")
	public Result carReturnedIsTrue(@RequestParam("carMaintenanceId") int carMaintenanceId) {
		return this.carMaintenanceService.CarAtMaintenanceReturnedIsTrue(carMaintenanceId);
	}
}
