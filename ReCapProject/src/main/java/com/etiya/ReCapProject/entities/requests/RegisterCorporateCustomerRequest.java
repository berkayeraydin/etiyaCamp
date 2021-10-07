package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCorporateCustomerRequest {

	@NotBlank
	@NotNull
	private String companyName;

	@NotBlank
	@NotNull
	@Size(min = 10,max = 10)
	private String taxNumber;

	@NotBlank
	@NotNull
	@Email
	private String email;

	@NotBlank
	@NotNull
	private String password;

	@NotBlank
	@NotNull
	private String passwordConfirm;
}
