package com.etiya.ReCapProject.entities.dtos;

import java.util.List;

import com.etiya.ReCapProject.entities.concretes.CarImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailDto {
	private String carName;

	private String brandName;

	private String colorName;

	private double dailyPrice;
	
	private List<CarImage> carImages;
	
}
