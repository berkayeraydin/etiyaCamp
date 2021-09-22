package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.abstracts.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarRequest;

public interface CarService {
	
	DataResult<List<Car>> getAll();

	DataResult<Car> getById(int carId);

	Result add(CreateCarRequest createCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);
	
	DataResult<List<CarDetailDto>> getAllCarsDetails();
	
	DataResult<CarDetailDto> getCarDetailsByCarId(int carId);
	
	DataResult<List<Car>> getCarsByColorId(int colorId);
	
	DataResult<List<Car>> getCarsByBrandId(int brandId);
	
	
}
