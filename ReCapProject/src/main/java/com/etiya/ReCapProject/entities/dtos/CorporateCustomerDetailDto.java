package com.etiya.ReCapProject.entities.dtos;

import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerDetailDto implements CustomerDto{

	private String companyName;
	
	private String taxNumber;
	
	private String email;

}
