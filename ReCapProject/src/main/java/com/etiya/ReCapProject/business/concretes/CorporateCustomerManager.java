package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCorporateCustomerRequest;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private UserService userService;

	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, UserService userService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<CorporateCustomer>> getAll() {
		return new SuccessDataResult<List<CorporateCustomer>>(this.corporateCustomerDao.findAll(),
				Messages.CustomersListed);
	}

	@Override
	public DataResult<CorporateCustomer> getById(int corporateCustomerId) {
		return new SuccessDataResult<CorporateCustomer>(this.corporateCustomerDao.getById(corporateCustomerId),
				Messages.CustomerListed);
	}

	@Override
	public DataResult<CorporateCustomerDetailDto> getCorporateCustomerDetailsById(int corporateCustomerId) {

		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);

		CorporateCustomerDetailDto corporateCustomerDetailDto = new CorporateCustomerDetailDto();
		corporateCustomerDetailDto.setCompanyName(corporateCustomer.getCompanyName());
		corporateCustomerDetailDto.setTaxNumber(corporateCustomer.getTaxNumber());

		return new SuccessDataResult<CorporateCustomerDetailDto>(corporateCustomerDetailDto,
				Messages.CorporateCustomerDetail);
	}

	@Override
	public DataResult<CorporateCustomer> getByApplicationUser_UserId(int applicationUserId) {
		return new SuccessDataResult<CorporateCustomer>(
				this.corporateCustomerDao.getByApplicationUser_UserId(applicationUserId));
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {

		ApplicationUser applicationUser = this.userService.getById(createCorporateCustomerRequest.getUserId())
				.getData();

		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setCompanyName(createCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(createCorporateCustomerRequest.getTaxNumber());

		corporateCustomer.setApplicationUser(applicationUser);

		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {

		CorporateCustomer corporateCustomer = this.corporateCustomerDao
				.getById(updateCorporateCustomerRequest.getCorporateCustomerId());

		corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());

		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {

		CorporateCustomer corporateCustomer = this.corporateCustomerDao
				.getById(deleteCorporateCustomerRequest.getCorporateCustomerId());

		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.CustomerDeleted);
	}

	@Override
	public Result existsByUserId(int applicationUserId) {

		if (this.corporateCustomerDao.existsByApplicationUser_UserId(applicationUserId)) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}

}
