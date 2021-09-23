package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateIndividualCustomerRequest;

public interface IndividualCustomerService {
	
	DataResult<List<IndividualCustomer>> getAll();

	DataResult<IndividualCustomer> getById(int individualCustomerId);

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	
	Result existsByUserId(int applicationUserId);
	
	DataResult<IndividualCustomer> getByApplicationUser_UserId(int applicationUserId);
	
	DataResult<IndividualCustomerDetailDto> getIndividualCustomerDetailsById(int individualCustomerId);
}
