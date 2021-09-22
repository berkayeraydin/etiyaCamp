package com.etiya.ReCapProject.business.concretes;

import java.util.List;

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
import com.etiya.ReCapProject.entities.requests.CreateCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCorporateCustomerRequest;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	
	private CorporateCustomerDao corporateCustomerDao;
	private UserService userService; 

	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, UserService userService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<CorporateCustomer>> getAll() {

		return new SuccessDataResult<List<CorporateCustomer>>(this.corporateCustomerDao.findAll(), Messages.CustomersListed);
	}

	@Override
	public DataResult<CorporateCustomer> getById(int corporatelCustomerId) {
		return new SuccessDataResult<CorporateCustomer>(this.corporateCustomerDao.getById(corporatelCustomerId), Messages.CustomerListed);
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		
        ApplicationUser applicationUser = this.userService.getById(createCorporateCustomerRequest.getUserId()).getData();
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setCompanyName(createCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(createCorporateCustomerRequest.getTaxNumber());
		
		corporateCustomer.setApplicationUser(applicationUser);
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		
		ApplicationUser applicationUser = this.userService.getById(updateCorporateCustomerRequest.getUserId()).getData();
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());
		
		corporateCustomer.setApplicationUser(applicationUser);
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CustomerAdded);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(deleteCorporateCustomerRequest.getCorporateCustomerId());
		
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

	@Override
	public DataResult<CorporateCustomerDetailDto> getCorporateCustomerDetailsById(int corporateCustomerId) {
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);
		
		CorporateCustomerDetailDto corporateCustomerDetailDto = new CorporateCustomerDetailDto();
		corporateCustomerDetailDto.setCompanyName(corporateCustomer.getCompanyName());

		return new SuccessDataResult<CorporateCustomerDetailDto>(corporateCustomerDetailDto,"bireysel müşteri detayları");
	}

}
