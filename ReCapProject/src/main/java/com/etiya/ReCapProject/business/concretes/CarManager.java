package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.abstracts.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarRequest;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ColorService colorService;
	private BrandService brandService;

	@Autowired
	public CarManager(CarDao carDao, ColorService colorService, BrandService brandService) {
		super();
		this.carDao = carDao;
		this.colorService = colorService;
		this.brandService = brandService;
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
	public Result add(CreateCarRequest createCarRequest) {

		Brand brand = this.brandService.getById(createCarRequest.getBrandId()).getData();

		Color color = this.colorService.getById(createCarRequest.getColorId()).getData();

		Car car = new Car();
		car.setCarName(createCarRequest.getCarName());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setModelYear(createCarRequest.getModelYear());
		car.setDescription(createCarRequest.getDescripton());

		car.setBrand(brand);
		car.setColor(color);

		this.carDao.save(car);
		return new SuccessResult(Messages.CarAdded);

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Brand brand = this.brandService.getById(updateCarRequest.getBrandId()).getData();

		Color color = this.colorService.getById(updateCarRequest.getColorId()).getData();

		Car car = this.carDao.getById(updateCarRequest.getCarId());
		car.setCarName(updateCarRequest.getCarName());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setDescription(updateCarRequest.getDescripton());

		car.setBrand(brand);
		car.setColor(color);

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
	public DataResult<List<CarDetailDto>> getAllCarsDetails() {

		return new SuccessDataResult<List<CarDetailDto>>(null,
				Messages.CarDetailsListed);
	}

	@Override
	public DataResult<CarDetailDto> getCarDetailsByCarId(int carId) {
		
		
		Car car = this.carDao.getById(carId);
		List<CarImage> carImages = car.getCarImages();
		
		CarDetailDto carDetailDto = new CarDetailDto();
		carDetailDto.setCarName(car.getCarName());
		carDetailDto.setDailyPrice(car.getDailyPrice());
		carDetailDto.setBrandName(car.getBrand().getBrandName());
		carDetailDto.setColorName(car.getColor().getColorName());
		
		carDetailDto.setCarImages(carImages);
		
		return new SuccessDataResult<CarDetailDto>(carDetailDto,
				Messages.CarDetailsListed);
	}

	@Override
	public DataResult<List<Car>> getCarsByColorId(int colorId) {
		return new SuccessDataResult<List<Car>>(this.carDao.getByColor_ColorId(colorId), Messages.CarsListedByColor);
	}

	@Override
	public DataResult<List<Car>> getCarsByBrandId(int brandId) {
		return new SuccessDataResult<List<Car>>(this.carDao.getByBrand_BrandId(brandId), Messages.CarsListedByBrand);
	}

}
