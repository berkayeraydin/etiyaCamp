package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;

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
	public Result add(@Valid @RequestBody CreateApplicationUserRequest createApplicationUserRequest) {
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setFirstName(createApplicationUserRequest.getFirstName());
		applicationUser.setLastName(createApplicationUserRequest.getLastName());
		applicationUser.setEmail(createApplicationUserRequest.getEmail());
		applicationUser.setPassword(createApplicationUserRequest.getPassword());
		
		this.applicationUserdao.save(applicationUser);
		return new SuccessResult(Messages.UserAdded);
	}

	@Override
	public Result update(@Valid @RequestBody CreateApplicationUserRequest createApplicationUserRequest) {
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setFirstName(createApplicationUserRequest.getFirstName());
		applicationUser.setLastName(createApplicationUserRequest.getLastName());
		applicationUser.setEmail(createApplicationUserRequest.getEmail());
		applicationUser.setPassword(createApplicationUserRequest.getPassword());
		
		this.applicationUserdao.save(applicationUser);
		return new SuccessResult(Messages.UserUpdated);
	}

	@Override
	public Result delete(int userId) {
		this.applicationUserdao.deleteById(userId);
		return new SuccessResult(Messages.UserDeleted);
	}

}
