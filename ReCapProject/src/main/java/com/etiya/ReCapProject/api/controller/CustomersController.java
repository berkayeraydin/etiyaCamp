package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.etiya.ReCapProject.entities.requests.DeleteCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCustomerRequest;

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
	public Result add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
		return this.customerService.add(createCustomerRequest);
		
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCustomerRequest updateCustomerRequest) {
		
		return this.customerService.update(updateCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
		return this.delete(deleteCustomerRequest);
		
	}
	
	@GetMapping("/getById")
	public DataResult<Customer> getById(int customerId){
		
		return this.customerService.getById(customerId);
	}
}
