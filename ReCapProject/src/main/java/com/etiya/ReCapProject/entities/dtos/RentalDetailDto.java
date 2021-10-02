package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailDto {

	private Date rentDate;

	private Date returnDate;

	private long rentKilometer;

	private long returnKilometer;

	private String rentCityName;

	private String returnCityName;

	private CarDetailDto carDetailDto;

	private List<RentalAdditionalDetailDto> rentalAdditionalDetailDtos;

}

