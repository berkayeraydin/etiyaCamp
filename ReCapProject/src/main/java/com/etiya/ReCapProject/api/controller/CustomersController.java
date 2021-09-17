package com.etiya.ReCapProject.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CustomerService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Customer;
import com.etiya.ReCapProject.entities.requests.CreateCustomerRequest;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomersController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Customer>> getAll(){
		
		return this.customerService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCustomerRequest createCustomerRequest) {
		return this.customerService.add(createCustomerRequest);
		
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody CreateCustomerRequest createCustomerRequest) {
		
		return this.customerService.update(createCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(int customerId) {
		return this.delete(customerId);
		
	}
	
	@GetMapping("/getById")
	public DataResult<Customer> getById(int customerId){
		
		return this.customerService.getById(customerId);
	}
}
