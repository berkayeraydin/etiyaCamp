package com.etiya.ReCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalAdditionalDetailDto {

	private String rentalAdditionalName;

	private double dailyPrice;
}
