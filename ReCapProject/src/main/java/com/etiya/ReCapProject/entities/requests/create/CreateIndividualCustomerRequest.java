package com.etiya.ReCapProject.entities.requests.create;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	
	@NotBlank
	@NotNull
	private String firstName;
	
	@NotBlank
	@NotNull
	private String lastName;
	
	@NotBlank
	@NotNull
	private String nationalIdentityNumber;
	
	@NotNull
	private int userId;
}
