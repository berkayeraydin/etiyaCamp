package com.etiya.ReCapProject.entities.requests.create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageInformationRequest {
	
	@JsonIgnore
	private int carDamageInformationId;
	
	@Size(max = 250)
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	private int carId;

}
