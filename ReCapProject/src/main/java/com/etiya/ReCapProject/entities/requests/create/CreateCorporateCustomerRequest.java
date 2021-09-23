package com.etiya.ReCapProject.entities.requests.create;


import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotNull
	private String companyName;
	
	@NotNull
	private String taxNumber;
	
	@NotNull
	private int userId;

}
