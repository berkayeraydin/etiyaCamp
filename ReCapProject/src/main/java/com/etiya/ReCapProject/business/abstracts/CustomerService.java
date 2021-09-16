package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Customer;

public interface CustomerService {
	
	DataResult<List<Customer>>getAll();
	
	DataResult<Customer> getById(int colorId);
	
	Result add(Customer customer);
	
	Result update(Customer customer);
	
	Result delete(Customer customer);
}
