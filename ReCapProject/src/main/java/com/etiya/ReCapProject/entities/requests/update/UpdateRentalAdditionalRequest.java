package com.etiya.ReCapProject.entities.requests.update;

import javax.validation.constraints.Min;
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
public class UpdateRentalAdditionalRequest {
	
	@NotNull
	private int rentalAdditionalId;
	
	@NotNull
	@NotBlank
	private String rentalAdditionalName;
	
	@NotNull
	@Min(0)
	private double dailyPrice;

}
