package com.etiya.ReCapProject.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;
import com.etiya.ReCapProject.entities.requests.RegisterCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.RegisterIndividualCustomerRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateIndividualCustomerRequest;

@Service
public class AuthManager implements AuthService {

	private UserService userService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private ModelMapperService modelMapperService;

	@Autowired
	public AuthManager(UserService userService, IndividualCustomerService individualCustomerService,
			CorporateCustomerService corporateCustomerService, ModelMapperService modelMapperService) {
		super();
		this.userService = userService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result individualCustomerRegister(RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {

		var result = BusinessRules.run(checkCustomerByEmail(registerIndividualCustomerRequest.getEmail()),
				checkPasswordByPasswordConfirm(registerIndividualCustomerRequest.getPassword(),
						registerIndividualCustomerRequest.getPasswordConfirm()));

		if (result != null) {
			return result;
		}

		CreateApplicationUserRequest createApplicationUserRequest =  modelMapperService.forRequest()
				.map(registerIndividualCustomerRequest, CreateApplicationUserRequest.class);

		this.userService.add(createApplicationUserRequest);

		CreateIndividualCustomerRequest createIndividualCustomerRequest =  modelMapperService.forRequest()
				.map(registerIndividualCustomerRequest, CreateIndividualCustomerRequest.class);

		createIndividualCustomerRequest
				.setUserId(this.userService.getByEmail(createApplicationUserRequest.getEmail()).getData().getUserId());

		this.individualCustomerService.add(createIndividualCustomerRequest);

		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result corporateCustomerRegister(RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {

		var result = BusinessRules.run(checkCustomerByEmail(registerCorporateCustomerRequest.getEmail()),
				checkPasswordByPasswordConfirm(registerCorporateCustomerRequest.getPassword(),
						registerCorporateCustomerRequest.getPasswordConfirm()));

		if (result != null) {
			return result;
		}

		CreateApplicationUserRequest createApplicationUserRequest = modelMapperService.forRequest()
				.map(registerCorporateCustomerRequest, CreateApplicationUserRequest.class);

		this.userService.add(createApplicationUserRequest);

		CreateCorporateCustomerRequest createCorporateCustomerRequest = modelMapperService.forRequest()
				.map(registerCorporateCustomerRequest, CreateCorporateCustomerRequest.class);
		createCorporateCustomerRequest.setUserId(
				this.userService.getByEmail(registerCorporateCustomerRequest.getEmail()).getData().getUserId());

		this.corporateCustomerService.add(createCorporateCustomerRequest);

		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result login(LoginRequest loginRequest) {

		var result = BusinessRules.run(this.checkCustomerEmailByEmailIsMatched(loginRequest),
				this.checkCustomerPasswordByPasswordIsMatched(loginRequest));

		if (result != null) {
			return result;
		}

		return new SuccessResult(Messages.SuccessfulLogin);
	}

	@Override
	public DataResult<CustomerDto> getCustomerDtoByEmail(String email) {

		ApplicationUser applicationUser = this.userService.getByEmail(email).getData();

		if (this.individualCustomerService.existsByUserId(this.userService.getByEmail(email).getData().getUserId())
				.isSuccess()) {

			IndividualCustomer individualCustomer = this.individualCustomerService
					.getByApplicationUser_UserId(applicationUser.getUserId()).getData();

			IndividualCustomerDetailDto individualCustomerDetailDto = modelMapperService.forDto()
					.map(individualCustomer, IndividualCustomerDetailDto.class);
			individualCustomerDetailDto.setEmail(individualCustomer.getApplicationUser().getEmail());

			return new SuccessDataResult<CustomerDto>(individualCustomerDetailDto, Messages.LoggedCustomer);
		}

		if (this.corporateCustomerService.existsByUserId(this.userService.getByEmail(email).getData().getUserId())
				.isSuccess()) {

			CorporateCustomer corporateCustomer = this.corporateCustomerService
					.getByApplicationUser_UserId(applicationUser.getUserId()).getData();

			CorporateCustomerDetailDto corporateCustomerDetailDto = modelMapperService.forDto().map(corporateCustomer,
					CorporateCustomerDetailDto.class);
			corporateCustomerDetailDto.setEmail(corporateCustomer.getApplicationUser().getEmail());

			return new SuccessDataResult<CustomerDto>(corporateCustomerDetailDto, Messages.LoggedCustomer);
		}

		return new ErrorDataResult<CustomerDto>("");

	}

	private Result checkCustomerEmailByEmailIsMatched(LoginRequest loginRequest) {

		if (this.userService.existsByEmail(loginRequest.getEmail()).isSuccess()) {
			return new ErrorResult(Messages.UserNotFound);
		}
		return new SuccessResult();
	}

	private Result checkCustomerPasswordByPasswordIsMatched(LoginRequest loginRequest) {

		if (checkCustomerEmailByEmailIsMatched(loginRequest).isSuccess()) {

			if (!this.userService.getByEmail(loginRequest.getEmail()).getData().getPassword()
					.equals(loginRequest.getPassword())) {
				return new ErrorResult(Messages.PasswordError);
			}
		}
		return new SuccessResult();
	}

	private Result checkCustomerByEmail(String email) {

		if (!this.userService.existsByEmail(email).isSuccess()) {
			return new ErrorResult(Messages.UserAlreadyExists);
		}
		return new SuccessResult();
	}

	private Result checkPasswordByPasswordConfirm(String password, String passwordConfirm) {

		if (!password.equals(passwordConfirm)) {
			return new ErrorResult(Messages.PasswordError);
		}
		return new SuccessResult();
	}

}
