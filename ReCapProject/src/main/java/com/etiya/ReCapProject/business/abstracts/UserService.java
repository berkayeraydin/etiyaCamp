package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;

public interface UserService {
	
	DataResult<List<ApplicationUser>>getAll();
	
	Result add(CreateApplicationUserRequest createApplicationUserRequest );
	
	Result update(CreateApplicationUserRequest createApplicationUserRequest);
	
	Result delete(int userId);
}
