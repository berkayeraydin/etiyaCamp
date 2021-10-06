package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CityDao;
import com.etiya.ReCapProject.entities.concretes.City;
import com.etiya.ReCapProject.entities.dtos.CityDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCityRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCityRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCityRequest;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<City>> getAll() {

		return new SuccessDataResult<List<City>>(this.cityDao.findAll(), Messages.CitiesListed);
	}

	@Override
	public DataResult<City> getById(int cityId) {

		return new SuccessDataResult<City>(this.cityDao.getById(cityId), Messages.CityListed);
	}

	@Override
	public DataResult<List<CityDetailDto>> getCitysDetail() {

		List<City> cities = this.cityDao.findAll();

		List<CityDetailDto> cityDetailDtos = cities.stream()
				.map(city -> modelMapperService.forDto().map(city, CityDetailDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CityDetailDto>>(cityDetailDtos, Messages.CitiesListed);
	}

	@Override
	public DataResult<CityDetailDto> getCityDetailById(int cityId) {

		City city = this.cityDao.getById(cityId);

		return new SuccessDataResult<CityDetailDto>(modelMapperService.forDto().map(city, CityDetailDto.class),
				Messages.CityListed);
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {

		var result = BusinessRules.run(this.checkCityByCityName(createCityRequest.getCityName()));

		if (result != null) {
			return result;
		}

		City city = modelMapperService.forRequest().map(createCityRequest, City.class);

		this.cityDao.save(city);
		return new SuccessResult(Messages.CityAdded);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {

		var result = BusinessRules.run(this.checkCityByCityName(updateCityRequest.getCityName()));

		if (result != null) {
			return result;
		}

		City city = this.cityDao.getById(updateCityRequest.getCityId());
		city.setCityName(updateCityRequest.getCityName());

		this.cityDao.save(city);
		return new SuccessResult(Messages.CityUpdated);
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {

		City city = this.cityDao.getById(deleteCityRequest.getCityId());

		this.cityDao.delete(city);
		return new SuccessResult(Messages.CityDeleted);
	}

	private Result checkCityByCityName(String cityName) {

		if (this.cityDao.existsByCityName(cityName)) {
			return new ErrorResult(Messages.CityIsFound);
		}
		return new SuccessResult();
	}

}
