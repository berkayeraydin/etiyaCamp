package com.etiya.ReCapProject.entities.dtos;

import java.util.List;

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

	private String cityName;

	private long kilometer;

	private String description;

	private int minFindeksScore;

	private double dailyPrice;

	private List<CarImageDetailDto> carcarImageDetailDtos;

}


