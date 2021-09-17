package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;

public interface CarService {
	
	DataResult<List<Car>> getAll();
	
	DataResult<Car> getById(int carId);
	
	Result add(CreateCarRequest createCarRequest);
	
	Result update(CreateCarRequest createCarRequest);
	
	Result delete(int carId);
	
	DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails();
}
