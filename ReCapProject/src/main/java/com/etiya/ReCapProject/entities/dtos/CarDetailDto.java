package com.etiya.ReCapProject.entities.dtos;

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
	
	//private CarImage carImage;
	
	//private String imagePath;
	
	//private List<CarImage> carImages;
}
