package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCorporateCustomerRequest;

public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomer>> getAll();

	DataResult<CorporateCustomer> getById(int corporatelCustomerId);

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	
	Result existsByUserId(int applicationUserId);
	
	DataResult<CorporateCustomerDetailDto> getCorporateCustomerDetailsById(int corporateCustomerId);
}
