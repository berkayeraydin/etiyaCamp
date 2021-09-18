package com.etiya.ReCapProject.entities.requests;

import javax.validation.constraints.Min;
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
public class CreateCarRequest {
	
	private String carName;
	
	@NotNull
	@NotBlank
	private String modelYear; 
	
	@NotNull
	@NotBlank
	@Min(0)
	private double dailyPrice; 
	
	@NotBlank
	@Size(max= 100)
	private String descripton; 
	
	@NotNull
	private int brandId;
	
	@NotNull
	private int colorId;
}