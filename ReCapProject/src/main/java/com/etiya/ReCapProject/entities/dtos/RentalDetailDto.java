package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;

import com.etiya.ReCapProject.entities.dtos.abstracts.CarDetailDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailDto {
	
	private Date rentDate;
	
	private Date returnDate;
	
	private CarDetailDto carDetailDto;
	
	private CustomerDto customerDto;
}

