package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.dataAccess.abstracts.ColorDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarRequest;

@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	private BrandDao brandDao;
	private ColorDao colorDao;
	
	@Autowired
	public CarManager(CarDao carDao,BrandDao brandDao,ColorDao colorDao) {
		super();
		this.carDao = carDao;
		this.brandDao = brandDao;
		this.colorDao = colorDao;
	}

	@Override
	public DataResult<List<Car>> getAll() {
		
		return new SuccessDataResult<List<Car>>(this.carDao.findAll(), Messages.CarsListed) ;
	}

	@Override
	public DataResult<Car> getById(int carId) {
		
		return new SuccessDataResult<Car>(this.carDao.getById(carId), Messages.CarsListed) ;
	}

	@Override
	public Result add( CreateCarRequest createCarRequest )  {
		
		Brand brand = this.brandDao.getById(createCarRequest.getBrandId());
		
		Color color = this.colorDao.getById(createCarRequest.getColorId());
		
		Car car = new Car();
		car.setCarName(createCarRequest.getCarName());
		car.setModelYear(createCarRequest.getModelYear());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setDescripton(createCarRequest.getDescripton());
		
		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CarAdded);
	}

	@Override
	public Result update( UpdateCarRequest updateCarRequest) {
		
		Brand brand = this.brandDao.getById(updateCarRequest.getBrandId());
		
		Color color = this.colorDao.getById(updateCarRequest.getColorId());
		
		Car car = this.carDao.getById(updateCarRequest.getCarId());
		car.setCarName(updateCarRequest.getCarName());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setDescripton(updateCarRequest.getDescripton());
		
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
	public DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails() {

		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarsWithBrandAndColorDetails(), Messages.CarDetailsListed) ;
	}
	

	@Override
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(this.carDao.existsCarImagesByCarId(carId), Messages.CarDetailsListed) ;
	}
	

	@Override
	public DataResult<List<Car>> getByBrandId(int brandId) {

		return new SuccessDataResult<List<Car>>(this.carDao.getByBrandId(brandId), Messages.CarsListed);
	}

	@Override
	public DataResult<List<Car>> getByColorId(int colorId) {

		return new SuccessDataResult<List<Car>>(this.carDao.getByColorId(colorId), Messages.CarsListed);
	}

	
}
