package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailDto {

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rentDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	private long rentKilometer;

	private long returnKilometer;

	private String takeCityName;

	private String returnCityName;

	private CarDetailDto carDetailDto;

	private List<RentalAdditionalDetailDto> rentalAdditionalDetailDtos;

}