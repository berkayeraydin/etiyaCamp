package com.etiya.ReCapProject.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInformationDto {
	
	@NotBlank
	@NotNull
	@Size(max = 25)
	private String cardName;

	@NotBlank
	@NotNull
	@Size(min = 16, max = 16)
	private String cardNumber;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 5)
	private String expirationDate;

	@NotBlank
	@NotNull
	@Size(min = 3, max = 3)
	private String cvv;
}
