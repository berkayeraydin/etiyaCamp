package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.DeleteApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.UpdateApplicationUserRequest;

@Service
public class UserManager implements UserService {
	
	private ApplicationUserDao applicationUserdao;
	
	@Autowired
	public UserManager(ApplicationUserDao applicationUserdao) {
		super();
		this.applicationUserdao = applicationUserdao;
	}

	@Override
	public DataResult<List<ApplicationUser>> getAll() {

		return new SuccessDataResult<List<ApplicationUser>>(this.applicationUserdao.findAll(), Messages.UsersListed);
	}

	@Override
	public Result add( CreateApplicationUserRequest createApplicationUserRequest) {
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setFirstName(createApplicationUserRequest.getFirstName());
		applicationUser.setLastName(createApplicationUserRequest.getLastName());
		applicationUser.setEmail(createApplicationUserRequest.getEmail());
		applicationUser.setPassword(createApplicationUserRequest.getPassword());
		
		this.applicationUserdao.save(applicationUser);
		return new SuccessResult(Messages.UserAdded);
	}

	@Override
	public Result update( UpdateApplicationUserRequest updateApplicationUserRequest) {
		
		ApplicationUser applicationUser = this.applicationUserdao.getById(updateApplicationUserRequest.getUserId());
		applicationUser.setFirstName(updateApplicationUserRequest.getFirstName());
		applicationUser.setLastName(updateApplicationUserRequest.getLastName());
		applicationUser.setEmail(updateApplicationUserRequest.getEmail());
		applicationUser.setPassword(updateApplicationUserRequest.getPassword());
		
		this.applicationUserdao.save(applicationUser);
		return new SuccessResult(Messages.UserUpdated);
	}

	@Override
	public Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest) {
		
		ApplicationUser applicationUser = this.applicationUserdao.getById(deleteApplicationUserRequest.getUserId());
		
		this.applicationUserdao.delete(applicationUser);
		return new SuccessResult(Messages.UserDeleted);
	}

}
