package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.City;
import com.etiya.ReCapProject.entities.requests.create.CreateCityRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCityRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCityRequest;

public interface CityService {
	
	DataResult<List<City>> getAll();

	DataResult<City> getById(int cityId);

	Result add(CreateCityRequest createCityRequest);

	Result update(UpdateCityRequest updateCityRequest);

	Result delete(DeleteCityRequest deleteCityRequest);

}
