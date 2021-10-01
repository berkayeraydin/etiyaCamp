package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;

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

	private CityDetailDto rentCityDetailDto;

	private CityDetailDto returnCityDetailDto;

	private CarDetailDto carDetailDto;

	private CustomerDto customerDto;
	
	private List<RentalAdditionalDetailDto> additionalDetailDtos;
}

