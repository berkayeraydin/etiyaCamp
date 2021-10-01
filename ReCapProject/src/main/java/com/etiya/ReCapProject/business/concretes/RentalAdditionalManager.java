package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.RentalAdditionalService;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalAdditionalDao;
import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalAdditionalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalAdditionalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalAdditionalRequest;

@Service
public class RentalAdditionalManager implements RentalAdditionalService {

	private RentalAdditionalDao rentalAdditionalDao;

	@Autowired
	public RentalAdditionalManager(RentalAdditionalDao rentalAdditionalDao) {
		super();
		this.rentalAdditionalDao = rentalAdditionalDao;
	}

	@Override
	public DataResult<List<RentalAdditional>> getAll() {
		return new SuccessDataResult<List<RentalAdditional>>(this.rentalAdditionalDao.findAll(),
				"Ek hizmetler listelendi");
	}

	@Override
	public DataResult<RentalAdditional> getById(int rentalAdditionalId) {
		return new SuccessDataResult<RentalAdditional>(this.rentalAdditionalDao.getById(rentalAdditionalId),
				"Ek hizmet listelendi");
	}

	@Override
	public Result add(CreateRentalAdditionalRequest createRentalAdditionalRequest) {

		var result = BusinessRules.run(this
				.checkRentalAdditionalByRentalAdditionalName(createRentalAdditionalRequest.getRentalAdditionalName()));

		if (result != null) {
			return result;
		}

		RentalAdditional rentalAdditional = new RentalAdditional();
		rentalAdditional.setRentalAdditionalName(createRentalAdditionalRequest.getRentalAdditionalName());
		rentalAdditional.setDailyPrice(createRentalAdditionalRequest.getDailyPrice());

		this.rentalAdditionalDao.save(rentalAdditional);

		return new SuccessResult("Ek hizmet eklendi");
	}

	@Override
	public Result update(UpdateRentalAdditionalRequest updateRentalAdditionalRequest) {

		var result = BusinessRules.run(this
				.checkRentalAdditionalByRentalAdditionalName(updateRentalAdditionalRequest.getRentalAdditionalName()));

		if (result != null) {
			return result;
		}

		RentalAdditional rentalAdditional = this.rentalAdditionalDao
				.getById(updateRentalAdditionalRequest.getRentalAdditionalId());
		rentalAdditional.setRentalAdditionalName(updateRentalAdditionalRequest.getRentalAdditionalName());
		rentalAdditional.setDailyPrice(updateRentalAdditionalRequest.getDailyPrice());

		this.rentalAdditionalDao.save(rentalAdditional);

		return new SuccessResult("Ek hizmet güncellendi");
	}

	@Override
	public Result delete(DeleteRentalAdditionalRequest deleteRentalAdditionalRequest) {

		RentalAdditional rentalAdditional = this.rentalAdditionalDao
				.getById(deleteRentalAdditionalRequest.getRentalAdditionalId());
		this.rentalAdditionalDao.delete(rentalAdditional);

		return new SuccessResult("Ek hizmet silindi");
	}

	private Result checkRentalAdditionalByRentalAdditionalName(String rentalAdditionalName) {
		if (this.rentalAdditionalDao.existsByRentalAdditionalName(rentalAdditionalName)) {
			return new ErrorResult("bu isimde ek hizmet mevcut");
		}
		return new SuccessResult();
	}

}
