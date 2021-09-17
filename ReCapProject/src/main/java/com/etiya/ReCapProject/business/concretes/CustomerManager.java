package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.etiya.ReCapProject.business.abstracts.CustomerService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.Customer;
import com.etiya.ReCapProject.entities.requests.CreateCustomerRequest;

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

		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(), Messages.CustomersListed);
	}

	@Override
	public Result add(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createCustomerRequest.getUserId());
		
		Customer customer = new Customer();
		customer.setCompanyName(createCustomerRequest.getCompanyName());
		customer.setApplicationUser(applicationUser);
		
		this.customerDao.save(customer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result update(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createCustomerRequest.getUserId());
		
		Customer customer = new Customer();
		customer.setCompanyName(createCustomerRequest.getCompanyName());
		customer.setApplicationUser(applicationUser);
		
		this.customerDao.save(customer);
		return new SuccessResult(Messages.CustomerUpdated);
	}

	@Override
	public Result delete(int customerId) {
		this.customerDao.deleteById(customerId);
		return new SuccessResult(Messages.CustomerDeleted);
	}

	@Override
	public DataResult<Customer> getById(int customerId) {

		return new SuccessDataResult<Customer>(this.customerDao.getById(customerId), Messages.CustomersListed);
	}

}
