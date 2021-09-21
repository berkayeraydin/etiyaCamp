package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.UpdateRentalRequest;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	
	private RentalService rentalService;
	
	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Rental>> getAll(){
		
		return this.rentalService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
		
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateRentalRequest updateRentalRequest) {
		
		return this.rentalService.update(updateRentalRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		return this.delete(deleteRentalRequest);
		
	}
	
	@GetMapping("/getById")
	public DataResult<Rental> getById(int rentalId){
		
		return this.rentalService.getById(rentalId);
	}
}
