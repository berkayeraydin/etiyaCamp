package com.etiya.ReCapProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarRequest;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private CarImageService carImageService;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarDao carDao, CarImageService carImageService, ModelMapperService modelMapperService) {
		super();
		this.carDao = carDao;
		this.carImageService = carImageService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<Car>> getAll() {

		return new SuccessDataResult<List<Car>>(this.carDao.findAll(), Messages.CarsListed);
	}

	@Override
	public DataResult<Car> getById(int carId) {

		return new SuccessDataResult<Car>(this.carDao.getById(carId), Messages.CarListed);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarsByCity_CityId(int cityId) {

		List<Car> cars = this.carDao.getByCity_CityIdAndIsListedIsTrue(cityId);

		List<CarDetailDto> carDetailDtos = new ArrayList<CarDetailDto>();

		for (Car car : cars) {

			CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
			carDetailDto
					.setCarcarImageDetailDtos(this.carImageService.getCarImageDetailByCarId(car.getCarId()).getData());

			carDetailDtos.add(carDetailDto);
		}

		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, Messages.CarsListedByCity);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarsByColorId(int colorId) {

		List<Car> cars = this.carDao.getByColor_ColorIdAndIsListedIsTrue(colorId);

		List<CarDetailDto> carDetailDtos = new ArrayList<CarDetailDto>();

		for (Car car : cars) {

			CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
			carDetailDto
					.setCarcarImageDetailDtos(this.carImageService.getCarImageDetailByCarId(car.getCarId()).getData());

			carDetailDtos.add(carDetailDto);
		}

		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, Messages.CarsListedByColor);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarsByBrandId(int brandId) {

		List<Car> cars = this.carDao.getByBrand_BrandIdAndIsListedIsTrue(brandId);

		List<CarDetailDto> carDetailDtos = new ArrayList<CarDetailDto>();

		for (Car car : cars) {

			CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
			carDetailDto
					.setCarcarImageDetailDtos(this.carImageService.getCarImageDetailByCarId(car.getCarId()).getData());

			carDetailDtos.add(carDetailDto);
		}

		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, Messages.CarsListedByBrand);
	}

	@Override
	public DataResult<List<CarDetailDto>> getAllCarsDetails() {

		List<Car> cars = this.carDao.getByIsListedIsTrue();

		List<CarDetailDto> carDetailDtos = new ArrayList<CarDetailDto>();

		for (Car car : cars) {

			CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
			carDetailDto
					.setCarcarImageDetailDtos(this.carImageService.getCarImageDetailByCarId(car.getCarId()).getData());

			carDetailDtos.add(carDetailDto);
		}

		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, Messages.CarDetailsListed);
	}

	@Override
	public DataResult<CarDetailDto> getCarDetailsByCarId(int carId) {

		Car car = this.carDao.getById(carId);

		CarDetailDto carDetailDto = modelMapperService.forDto().map(car, CarDetailDto.class);
		carDetailDto.setCarcarImageDetailDtos(this.carImageService.getCarImageDetailByCarId(car.getCarId()).getData());

		return new SuccessDataResult<CarDetailDto>(carDetailDto, Messages.CarDetailsListed);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.CarAdded);

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carDao.save(car);
		return new SuccessResult(Messages.CarUpdated);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {

		Car car = this.carDao.getById(deleteCarRequest.getCarId());

		this.carDao.delete(car);
		return new SuccessResult(Messages.CarDeleted);
	}

	@Override
	public Result checkCarIsInGallery(int carId) {

		Car car = this.carDao.getById(carId);

		if (!car.isListed()) {
			return new ErrorResult(Messages.CarIsNotInGalery);
		}

		return new SuccessResult();
	}

	@Override
	public Result carListedIsTrue(int carId) {

		Car car = this.carDao.getById(carId);
		car.setListed(true);

		return new SuccessResult(Messages.CarCanListed);
	}

	@Override
	public Result carListedIsFalse(int carId) {

		Car car = this.carDao.getById(carId);
		car.setListed(false);

		return new SuccessResult(Messages.CarCantListed);
	}

}