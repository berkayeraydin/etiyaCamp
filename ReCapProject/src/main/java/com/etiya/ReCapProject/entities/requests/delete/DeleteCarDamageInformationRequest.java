package com.etiya.ReCapProject.entities.requests.delete;

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
public class DeleteCarDamageInformationRequest {
	
	@NotNull
	@NotBlank
	private int carDamageInformationId;
}
