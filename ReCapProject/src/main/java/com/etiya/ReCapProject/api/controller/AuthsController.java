package com.etiya.ReCapProject.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;
import com.etiya.ReCapProject.entities.requests.LoginRequest;
import com.etiya.ReCapProject.entities.requests.RegisterCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.RegisterIndividualCustomerRequest;

@RestController
@RequestMapping("api/auths")
public class AuthsController {
	private AuthService authService;

	@Autowired
	public AuthsController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/individualCustomerRegister")
	Result individualCustomerRegister(
			@Valid @RequestBody RegisterIndividualCustomerRequest registerIndividualCustomerRequest) {
		return this.authService.individualCustomerRegister(registerIndividualCustomerRequest);
	}

	@PostMapping("/corporateCustomerRegister")
	Result corporateCustomerRegister(
			@Valid @RequestBody RegisterCorporateCustomerRequest registerCorporateCustomerRequest) {
		return this.authService.corporateCustomerRegister(registerCorporateCustomerRequest);
	}

	@PostMapping("/login")
	Result login(@Valid @RequestBody LoginRequest loginRequest) {
		return this.authService.login(loginRequest);
	}

	@GetMapping("/getCustomerDtoByEmail")
	DataResult<CustomerDto> getCustomerDtoByEmail(@RequestParam("email") String email) {
		return this.authService.getCustomerDtoByEmail(email);
	}

}
