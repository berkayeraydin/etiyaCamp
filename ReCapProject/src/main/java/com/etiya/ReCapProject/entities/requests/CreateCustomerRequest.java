package com.etiya.ReCapProject.entities.requests;


import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
	
	
	private String companyName;
	
	@NotNull
	private int userId;
}
