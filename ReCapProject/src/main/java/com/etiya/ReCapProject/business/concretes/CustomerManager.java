package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CustomerService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.ReCapProject.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<List<Customer>> getAll() {

		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(), "Basariyla Listelendi.");
	}

	@Override
	public Result add(Customer customer) {
		this.customerDao.save(customer);
		return new SuccessResult("Basariyla Eklendi.");
	}

	@Override
	public Result update(Customer customer) {
		this.customerDao.save(customer);
		return new SuccessResult("Basariyla Guncellendi.");
	}

	@Override
	public Result delete(Customer customer) {
		this.customerDao.delete(customer);
		return new SuccessResult("Basariyla Silindi.");
	}

	@Override
	public DataResult<Customer> getById(int customerId) {

		return new SuccessDataResult<Customer>(this.customerDao.getById(customerId), "Id e gore listelendi.");
	}

}
