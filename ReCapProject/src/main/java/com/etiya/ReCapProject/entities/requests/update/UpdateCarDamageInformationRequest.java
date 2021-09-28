package com.etiya.ReCapProject.entities.requests.update;

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
public class UpdateCarDamageInformationRequest {
	
	@NotNull
	private int carDamageInformationId;
	
	@Size(max = 250)
	@NotNull
	@NotBlank
	private String description;
}

