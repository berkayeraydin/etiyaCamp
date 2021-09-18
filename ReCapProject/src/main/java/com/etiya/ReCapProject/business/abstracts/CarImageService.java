package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

public interface CarImageService {
	
	DataResult<List<CarImage>>getAll();
	
	Result add(CreateCarImageRequest createCarImageRequest);
	
	Result update(UpdateCarImageRequest updateCarImageRequest);
	
	Result delete(int carImageId);
	
	DataResult<List<CarImage>> getByCar_CarId(int carId);
}
