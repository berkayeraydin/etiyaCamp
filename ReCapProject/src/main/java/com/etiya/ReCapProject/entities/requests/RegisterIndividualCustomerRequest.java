package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIndividualCustomerRequest {
	
	@NotBlank
	@NotNull
	private String firstName;
	
	@NotBlank
	@NotNull
	private String lastName;
	
	@NotBlank
	@NotNull
	@Size(min = 11,max = 11)
	private String nationalIdentityNumber;
	
	@NotBlank
	@Email
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull
	private String password;
	
	@NotBlank
	@NotNull
	private String passwordConfirm;
}
