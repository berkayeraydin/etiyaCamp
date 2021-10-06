package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.create.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateApplicationUserRequest;

@Service
public class UserManager implements UserService {

	private ApplicationUserDao applicationUserDao;
	private ModelMapper modelMapper;

	@Autowired
	public UserManager(ApplicationUserDao applicationUserDao,ModelMapper modelMapper) {
		super();
		this.applicationUserDao = applicationUserDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<ApplicationUser>> getAll() {
		
		return new SuccessDataResult<List<ApplicationUser>>(this.applicationUserDao.findAll(), Messages.UsersListed);
	}

	@Override
	public DataResult<ApplicationUser> getById(int applicationUserId) {
		
		return new SuccessDataResult<ApplicationUser>(this.applicationUserDao.getById(applicationUserId));
	}

	@Override
	public Result add(CreateApplicationUserRequest createApplicationUserRequest) {

		var result = BusinessRules.run(this.existsByEmail(createApplicationUserRequest.getEmail()));

		if (result != null) {
			return result;
		}

//		ApplicationUser applicationUser = new ApplicationUser();
//		applicationUser.setEmail(createApplicationUserRequest.getEmail());
//		applicationUser.setPassword(createApplicationUserRequest.getPassword());
		ApplicationUser applicationUser = modelMapper.map(createApplicationUserRequest, ApplicationUser.class);

		this.applicationUserDao.save(applicationUser);
		return new SuccessResult(Messages.UserAdded);
	}

	@Override
	public Result update(UpdateApplicationUserRequest updateApplicationUserRequest) {

		var result = BusinessRules.run(this.existsByEmail(updateApplicationUserRequest.getEmail()));

		if (result != null) {
			return result;
		}
		
		ApplicationUser applicationUser = modelMapper.map(updateApplicationUserRequest, ApplicationUser.class);
//		ApplicationUser applicationUser = this.applicationUserDao.getById(updateApplicationUserRequest.getUserId());
//		applicationUser.setEmail(updateApplicationUserRequest.getEmail());
//		applicationUser.setPassword(updateApplicationUserRequest.getPassword());

		this.applicationUserDao.save(applicationUser);
		return new SuccessResult(Messages.UserUpdated);
	}

	@Override
	public Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest) {

		ApplicationUser applicationUser = this.applicationUserDao.getById(deleteApplicationUserRequest.getUserId());

		this.applicationUserDao.delete(applicationUser);
		return new SuccessResult(Messages.UserDeleted);
	}

	@Override
	public DataResult<ApplicationUser> getByEmail(String email) {
		
		return new SuccessDataResult<ApplicationUser>(this.applicationUserDao.getByEmail(email));
	}

	@Override
	public Result existsByEmail(String email) {
		
		if (this.applicationUserDao.existsByEmail(email)) {
			return new ErrorResult(Messages.EmailAlreadyExists);
		}		
		return new SuccessResult();
	}

}
