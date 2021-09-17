package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.requests.CreateCarRequest;

@Service
public class CarManager implements CarService{
	
	private CarDao carDao;
	
	@Autowired
	public CarManager(CarDao carDao) {
		super();
		this.carDao = carDao;
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
	public Result add(@Valid @RequestBody CreateCarRequest createCarRequest) {
		
		Brand brand = new Brand();
		brand.setBrandId(createCarRequest.getBrandId());
		
		Color color = new Color();
		color.setColorId(createCarRequest.getColorId());
		
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
	public Result update(@Valid @RequestBody CreateCarRequest createCarRequest) {
		Brand brand = new Brand();
		brand.setBrandId(createCarRequest.getBrandId());
		
		Color color = new Color();
		color.setColorId(createCarRequest.getColorId());
		
		Car car = new Car();
		car.setCarName(createCarRequest.getCarName());
		car.setModelYear(createCarRequest.getModelYear());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setDescripton(createCarRequest.getDescripton());
		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
		return new SuccessResult(Messages.CarUpdated);
	}

	@Override
	public Result delete(int carId) {

		this.carDao.deleteById(carId);
		return new SuccessResult(Messages.CarDeleted);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarWithBrandAndColorDetails() {

		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarsWithBrandAndColorDetails(), Messages.CarDetailsListed) ;
	}

}
