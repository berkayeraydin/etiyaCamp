package com.etiya.ReCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.CreateIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.LoginRequest;
import com.etiya.ReCapProject.entities.requests.RegisterCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.RegisterIndividualCustomerRequest;

@Service
public class AuthManager implements AuthService {
	
	private UserService userService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	@Autowired
	public AuthManager(UserService userService, IndividualCustomerService individualCustomerService,
			CorporateCustomerService corporateCustomerService) {
		super();
		this.userService = userService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		var result = BusinessRules.run();

		if (result != null) {
			return result;
		}

		CreateApplicationUserRequest createApplicationUserRequest = new CreateApplicationUserRequest();
		createApplicationUserRequest.setEmail(registerIndividualCustomerRequest.getEmail());
		createApplicationUserRequest.setPassword(registerIndividualCustomerRequest.getPassword());

		this.userService.add(createApplicationUserRequest);

		CreateIndividualCustomerRequest createIndividualCustomerRequest = new CreateIndividualCustomerRequest();
		createIndividualCustomerRequest.setFirstName(registerIndividualCustomerRequest.getFirstName());
		createIndividualCustomerRequest.setLastName(registerIndividualCustomerRequest.getLastName());
		createIndividualCustomerRequest
				.setNationalIdentityNumber(registerIndividualCustomerRequest.getNationalIdentityNumber());
		createIndividualCustomerRequest.setUserId(
				this.userService.getByEmail(registerIndividualCustomerRequest.getEmail()).getData().getUserId());

		this.individualCustomerService.add(createIndividualCustomerRequest);

		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		var result = BusinessRules.run();

		if (result != null) {
			return result;
		}
		CreateApplicationUserRequest createApplicationUserRequest = new CreateApplicationUserRequest();
		createApplicationUserRequest.setEmail(registerCorporateCustomerRequest.getEmail());
		createApplicationUserRequest.setPassword(registerCorporateCustomerRequest.getPassword());

		this.userService.add(createApplicationUserRequest);

		CreateCorporateCustomerRequest createCorporateCustomerRequest = new CreateCorporateCustomerRequest();
		createCorporateCustomerRequest.setCompanyName(registerCorporateCustomerRequest.getCompanyName());
		createCorporateCustomerRequest.setTaxNumber(registerCorporateCustomerRequest.getTaxNumber());
		createCorporateCustomerRequest.setUserId(
				this.userService.getByEmail(registerCorporateCustomerRequest.getEmail()).getData().getUserId());

		this.corporateCustomerService.add(createCorporateCustomerRequest);

		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result login(LoginRequest loginRequest) {
		if (!this.userService.existsByEmail(loginRequest.getEmail()).isSuccess()) {
			return new ErrorResult("Bu email ile kayıtlı kullanıcı bulunamadı");

		}
		if (!this.userService.getByEmail(loginRequest.getEmail()).getData()
				.getPassword().equals(loginRequest.getPassword())) {
			return new ErrorResult("Girdiğiniz şifre yanlış");
		}

		return new SuccessResult("Giriş başarılı");
	}

	
}
