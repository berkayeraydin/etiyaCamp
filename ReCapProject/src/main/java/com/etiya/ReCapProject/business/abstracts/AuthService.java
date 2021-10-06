package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;
import com.etiya.ReCapProject.entities.requests.RegisterCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.RegisterIndividualCustomerRequest;

public interface AuthService {
	
	Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest);

	Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest);

	Result login(LoginRequest loginRequest);
	
	DataResult<CustomerDto> getCustomerDtoByEmail(String email);
}
