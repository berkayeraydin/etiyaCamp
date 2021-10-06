package com.etiya.ReCapProject.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarMaintenanceService;
import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarMaintenanceDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarMaintenance;
import com.etiya.ReCapProject.entities.dtos.CarMaintenanceDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarMaintenanceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarMaintenanceRequest;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private CarService carService;
	private ModelMapperService modelMapperService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, CarService carService,
			ModelMapperService modelMapperService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.carService = carService;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CarMaintenance>> getAll() {

		return new SuccessDataResult<List<CarMaintenance>>(this.carMaintenanceDao.findAll(),
				Messages.CarMaintenancesListed);
	}

	@Override
	public DataResult<CarMaintenance> getById(int carMaintenanceId) {

		return new SuccessDataResult<CarMaintenance>(this.carMaintenanceDao.getById(carMaintenanceId),
				Messages.CarMaintenancesListed);
	}

	@Override
	public DataResult<List<CarMaintenanceDetailDto>> getCarMaintenancesDetail() {

		List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAll();

		List<CarMaintenanceDetailDto> carMaintenanceDetailDtos = new ArrayList<CarMaintenanceDetailDto>();

		for (CarMaintenance carMaintenance : carMaintenances) {

			CarMaintenanceDetailDto carMaintenanceDetailDto = modelMapperService.forDto().map(carMaintenance,
					CarMaintenanceDetailDto.class);

			carMaintenanceDetailDto.setCarDetailDto(
					this.carService.getCarDetailsByCarId(carMaintenance.getCar().getCarId()).getData());

			carMaintenanceDetailDtos.add(carMaintenanceDetailDto);
		}

		return new SuccessDataResult<List<CarMaintenanceDetailDto>>(carMaintenanceDetailDtos,
				Messages.CarMaintenancesListed);
	}

	@Override
	public DataResult<CarMaintenanceDetailDto> getCarMaintenanceDetailById(int carMaintenanceId) {

		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);

		CarMaintenanceDetailDto carMaintenanceDetailDto = modelMapperService.forDto().map(carMaintenance,
				CarMaintenanceDetailDto.class);

		carMaintenanceDetailDto
				.setCarDetailDto(this.carService.getCarDetailsByCarId(carMaintenance.getCar().getCarId()).getData());

		return new SuccessDataResult<CarMaintenanceDetailDto>(carMaintenanceDetailDto, Messages.CarMaintenancesListed);
	}

	@Override
	public DataResult<List<CarMaintenanceDetailDto>> getCarMaintenanceDetailByCarId(int carId) {

		List<CarMaintenance> carMaintenances = this.carMaintenanceDao.getByCar_CarId(carId);

		List<CarMaintenanceDetailDto> carMaintenanceDetailDtos = new ArrayList<CarMaintenanceDetailDto>();

		for (CarMaintenance carMaintenance : carMaintenances) {

			CarMaintenanceDetailDto carMaintenanceDetailDto = modelMapperService.forDto().map(carMaintenance,
					CarMaintenanceDetailDto.class);

			carMaintenanceDetailDto.setCarDetailDto(
					this.carService.getCarDetailsByCarId(carMaintenance.getCar().getCarId()).getData());

			carMaintenanceDetailDtos.add(carMaintenanceDetailDto);
		}

		return new SuccessDataResult<List<CarMaintenanceDetailDto>>(carMaintenanceDetailDtos,
				Messages.CarMaintenancesListed);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {

		var result = BusinessRules.run(this.carService.checkCarIsInGallery(createCarMaintenanceRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(createCarMaintenanceRequest.getCarId()).getData();
		this.carService.carListedIsFalse(car.getCarId());

		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);

		carMaintenance.setCar(car);

		this.carMaintenanceDao.save(carMaintenance);

		return new SuccessResult(Messages.CarMaintenanceAdded);
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {

		CarMaintenance carMaintenance = this.carMaintenanceDao
				.getById(updateCarMaintenanceRequest.getCarMaintenanceId());
		carMaintenance.setDescription(updateCarMaintenanceRequest.getDescription());
		carMaintenance.setReturnDate(updateCarMaintenanceRequest.getReturnDate());

		this.carMaintenanceDao.save(carMaintenance);

		return new SuccessResult(Messages.CarMaintenanceUpdated);
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {

		CarMaintenance carMaintenance = this.carMaintenanceDao
				.getById(deleteCarMaintenanceRequest.getCarMaintenanceId());

		this.carMaintenanceDao.delete(carMaintenance);

		return new SuccessResult(Messages.CarMaintenanceDeleted);
	}

	// Arabanın servisten dönüş işlemi
	@Override
	public Result CarAtMaintenanceReturnedIsTrue(int carMaintenanceId) {

		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);
		carMaintenance.setCarReturned(true);

		this.carService.carListedIsTrue(carMaintenance.getCar().getCarId());

		this.carMaintenanceDao.save(carMaintenance);

		return new SuccessResult(Messages.CarAtMaintenanceReturned);
	}

}
