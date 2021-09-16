package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;

public interface CarService {
	
	DataResult<List<Car>> getAll();
	
	DataResult<Car> getById(int carId);
	
	Result add(Car car);
	
	Result update(Car car);
	
	Result delete(Car car);
	
	DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails();
}
