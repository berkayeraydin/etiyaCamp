package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CarDamageInformation;
import com.etiya.ReCapProject.entities.dtos.CarDamageInformationDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarDamageInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarDamageInformationRequest;

public interface CarDamageInformationService {
	
	DataResult<List<CarDamageInformation>> getAll();

	DataResult<CarDamageInformation> getById(int carDamageInformationId);

	DataResult<List<CarDamageInformationDetailDto>> getCarDamageInformationsDetail();

	DataResult<CarDamageInformationDetailDto> getCarDamageInformationDetailById(int carDamageInformationId);

	DataResult<List<CarDamageInformationDetailDto>> getCarDamageInformationsByCarId(int carId);

	Result add(CreateCarDamageInformationRequest createCarDamageInformationRequest);

	Result update(UpdateCarDamageInformationRequest updateCarDamageInformationRequest);

	Result delete(DeleteCarDamageInformationRequest deleteCarDamageInformationRequest);

}
