package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CustomerService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.ReCapProject.dataAccess.abstracts.CustomerDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.Customer;
import com.etiya.ReCapProject.entities.requests.CreateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCustomerRequest;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	private ApplicationUserDao applicationUserDao;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao,ApplicationUserDao applicationUserDao) {
		super();
		this.customerDao = customerDao;
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public DataResult<List<Customer>> getAll() {

		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(), Messages.CustomersListed);
	}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) {
		
		ApplicationUser applicationUser = this.applicationUserDao.getById(createCustomerRequest.getUserId());
		
		Customer customer = new Customer();
		customer.setCompanyName(createCustomerRequest.getCompanyName());
		
		customer.setApplicationUser(applicationUser);
		
		this.customerDao.save(customer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result update(UpdateCustomerRequest updateCustomerRequest) {
		
		ApplicationUser applicationUser = this.applicationUserDao.getById(updateCustomerRequest.getUserId());
		
		Customer customer = this.customerDao.getById(updateCustomerRequest.getCustomerId());
		customer.setCompanyName(updateCustomerRequest.getCompanyName());
		
		customer.setApplicationUser(applicationUser);
		
		this.customerDao.save(customer);
		return new SuccessResult(Messages.CustomerUpdated);
	}

	@Override
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
		
		Customer customer = this.customerDao.getById(deleteCustomerRequest.getCustomerId());
		
		this.customerDao.delete(customer);
		return new SuccessResult(Messages.CustomerDeleted);
	}

	@Override
	public DataResult<Customer> getById(int customerId) {

		return new SuccessDataResult<Customer>(this.customerDao.getById(customerId), Messages.CustomersListed);
	}

}
