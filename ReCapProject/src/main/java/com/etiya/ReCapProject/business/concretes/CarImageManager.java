package com.etiya.ReCapProject.business.concretes;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.ErrorResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {
	
	private CarImageDao carImageDao;
	
	@Autowired
	public CarImageManager(CarImageDao carImageDao) {
		super();
		this.carImageDao = carImageDao;
	}

	@Override
	public DataResult<List<CarImage>> getAll() {

		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), Messages.CarImagesListed);
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) {
		
		var result = BusinnesRules.run(checkCarImagesCount(createCarImageRequest.getCarId(),5));
		
		if (result != null) {
			return result;
		}
		
		
		Date dateoperation = new java.sql.Date(new java.util.Date().getTime());
		String imageRandomName = java.util.UUID.randomUUID().toString();
		
		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());
		
		CarImage carImage = new  CarImage();
		carImage.setImagePath("carImages/" + imageRandomName);
		carImage.setCar(car);
	
		carImage.setDate(dateoperation);		
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded) ;
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) {
		
		Date dateoperation = new java.sql.Date(new java.util.Date().getTime());
		String imageRandomName = java.util.UUID.randomUUID().toString();
		
		Car car = new Car();
		car.setCarId(updateCarImageRequest.getCarId());
	
		
		CarImage carImage = new  CarImage();
		carImage.setImagePath("carImages/" + imageRandomName);
		carImage.setCar(car);
		carImage.setCarImageId(updateCarImageRequest.getCarImageId());
	
		carImage.setDate(dateoperation);
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated) ;
	}

	@Override
	public Result delete(int carImageId) {
		
		this.carImageDao.deleteById(carImageId);
		return new SuccessResult(Messages.CarImageDeleted);
	}
	
	private Result checkCarImagesCount(int carId,int limit) {
		
		if (this.carImageDao.countByCar_CarId(carId) >= limit) {
			return new ErrorResult(Messages.CarImagesCountOfCarError);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<CarImage>> getByCar_CarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(this.carImageDao.getByCar_CarId(carId), Messages.CarImagesListed);
	}
	
	
	

}
