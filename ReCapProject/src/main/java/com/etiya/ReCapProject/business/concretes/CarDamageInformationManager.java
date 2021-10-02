package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarDamageInformationService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDamageInformationDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarDamageInformation;
import com.etiya.ReCapProject.entities.requests.create.CreateCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarDamageInformationRequest;

@Service
public class CarDamageInformationManager implements CarDamageInformationService {

	private CarDamageInformationDao carDamageInformationDao;
	private CarService carService;

	@Autowired
	public CarDamageInformationManager(CarDamageInformationDao carDamageInformationDao, CarService carService) {
		super();
		this.carDamageInformationDao = carDamageInformationDao;
		this.carService = carService;
	}

	@Override
	public DataResult<List<CarDamageInformation>> getAll() {
		return new SuccessDataResult<List<CarDamageInformation>>(this.carDamageInformationDao.findAll(),
				Messages.CarDamageInformationsListed);
	}

	@Override
	public DataResult<CarDamageInformation> getById(int carDamageInformationId) {
		return new SuccessDataResult<CarDamageInformation>(this.carDamageInformationDao.getById(carDamageInformationId),
				Messages.CarDamageInformationListed);
	}

	@Override
	public DataResult<List<CarDamageInformation>> getCarDamageInformationsByCarId(int carId) {
		return new SuccessDataResult<List<CarDamageInformation>>(this.carDamageInformationDao.getByCar_CarId(carId),
				Messages.CarDamageInformationsListedByCar);
	}

	@Override
	public Result add(CreateCarDamageInformationRequest createCarDamageInformationRequest) {

		Car car = this.carService.getById(createCarDamageInformationRequest.getCarId()).getData();

		CarDamageInformation carDamageInformation = new CarDamageInformation();
		carDamageInformation.setDescription(createCarDamageInformationRequest.getDescription());

		carDamageInformation.setCar(car);

		this.carDamageInformationDao.save(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationAdded);
	}

	@Override
	public Result update(UpdateCarDamageInformationRequest updateCarDamageInformationRequest) {

		CarDamageInformation carDamageInformation = this.carDamageInformationDao
				.getById(updateCarDamageInformationRequest.getCarDamageInformationId());
		carDamageInformation.setDescription(updateCarDamageInformationRequest.getDescription());

		this.carDamageInformationDao.save(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationUpdated);
	}

	@Override
	public Result delete(DeleteCarDamageInformationRequest deleteCarDamageInformationRequest) {

		CarDamageInformation carDamageInformation = this.carDamageInformationDao
				.getById(deleteCarDamageInformationRequest.getCarDamageInformationId());
		this.carDamageInformationDao.delete(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationDeleted);
	}

}