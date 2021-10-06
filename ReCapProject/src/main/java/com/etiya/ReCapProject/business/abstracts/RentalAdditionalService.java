package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.dtos.RentalAdditionalDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalAdditionalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalAdditionalRequest;

public interface RentalAdditionalService {
	
	DataResult<List<RentalAdditional>> getAll();

	DataResult<RentalAdditional> getById(int rentalAdditionalId);
	
	DataResult<List<RentalAdditionalDetailDto>> getRentalAdditionalDetails();

	DataResult<RentalAdditionalDetailDto> getRentalAdditionalDetailsById(int rentalAdditionalId);

	Result add(CreateRentalAdditionalRequest createRentalAdditionalRequest);

	Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest);

	Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest);

}
