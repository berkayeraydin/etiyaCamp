package com.etiya.ReCapProject.entities.dtos;

import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDetailDto implements CustomerDto{

	private String firstName;

	private String lastName;
	
	private String nationalIdentityNumber;
	
	private String email;

}
