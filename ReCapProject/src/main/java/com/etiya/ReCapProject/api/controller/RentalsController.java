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

import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalRequest;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
	private RentalService rentalService;

	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Rental>> getAll() {
		return this.rentalService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<Rental> getById(@RequestParam("rentalId") int rentalId) {
		return this.rentalService.getById(rentalId);
	}

	@GetMapping("/getRentalDetailsByRentalId")
	public DataResult<RentalDetailDto> getRentalDetailsByRentalId(@RequestParam("rentalId") int rentalId) {
		return this.rentalService.getRentalDetailsByRentalId(rentalId);
	}

	@GetMapping("/getRentalsDetailByApplicationUserId")
	DataResult<List<RentalDetailDto>> getRentalsDetailByApplicationUserId(@RequestParam("applicationUserId") int applicationUserId) {
		return this.rentalService.getRentalsDetailByApplicationUserId(applicationUserId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}

	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@DeleteMapping("/delete")
	public Result delte(@Valid DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}

	@PostMapping("/carReturnedIsTrue")
	public Result carReturnedIsTrue(@Valid @RequestBody CarReturnedRequest carReturnedRequest) {
		return this.rentalService.carAtRentalReturnedIsTrue(carReturnedRequest);
	}
}