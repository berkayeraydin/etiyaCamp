package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarDamageInformationService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDamageInformationDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarDamageInformation;
import com.etiya.ReCapProject.entities.dtos.CarDamageInformationDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarDamageInformationRequest;

@Service
public class CarDamageInformationManager implements CarDamageInformationService {

	private CarDamageInformationDao carDamageInformationDao;
	private ModelMapper modelMapper;

	@Autowired
	public CarDamageInformationManager(CarDamageInformationDao carDamageInformationDao,ModelMapper modelMapper) {
		super();
		this.carDamageInformationDao = carDamageInformationDao;
		this.modelMapper = modelMapper ;
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
	public DataResult<List<CarDamageInformationDetailDto>> getCarDamageInformationsDetail() {
		
		List<CarDamageInformation> carDamageInformations = this.carDamageInformationDao.findAll();
		
		List<CarDamageInformationDetailDto> carDamageInformationDetailDto = carDamageInformations
				.stream().map(carDamageInformation -> modelMapper.map(carDamageInformation, CarDamageInformationDetailDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarDamageInformationDetailDto>>(carDamageInformationDetailDto, Messages.CarDamageInformationsListed);
	}

	@Override
	public DataResult<CarDamageInformationDetailDto> getCarDamageInformationsDetailId(int carDamageInformationId) {
		
		CarDamageInformation carDamageInformation = this.carDamageInformationDao.getById(carDamageInformationId);
		
		return new SuccessDataResult<CarDamageInformationDetailDto>(modelMapper.map(carDamageInformation, CarDamageInformationDetailDto.class), Messages.CarDamageInformationListed);
	}

	@Override
	public DataResult<List<CarDamageInformation>> getCarDamageInformationsByCarId(int carId) {
		
		return new SuccessDataResult<List<CarDamageInformation>>(this.carDamageInformationDao.getByCar_CarId(carId),
				Messages.CarDamageInformationsListedByCar);
	}

	@Override
	public Result add(CreateCarDamageInformationRequest createCarDamageInformationRequest) {

		Car car = new Car();
		car.setCarId(createCarDamageInformationRequest.getCarId());

		CarDamageInformation carDamageInformation = modelMapper.map(createCarDamageInformationRequest, CarDamageInformation.class);

		carDamageInformation.setCar(car);

		this.carDamageInformationDao.save(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationAdded);
	}

	@Override
	public Result update(UpdateCarDamageInformationRequest updateCarDamageInformationRequest) {

		CarDamageInformation carDamageInformation = modelMapper.map(updateCarDamageInformationRequest, CarDamageInformation.class);

		this.carDamageInformationDao.save(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationUpdated);
	}

	@Override
	public Result delete(DeleteCarDamageInformationRequest deleteCarDamageInformationRequest) {

		CarDamageInformation carDamageInformation = modelMapper.map(deleteCarDamageInformationRequest, CarDamageInformation.class);
		
		this.carDamageInformationDao.delete(carDamageInformation);

		return new SuccessResult(Messages.CarDamageInformationDeleted);
	}

}