package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.DeleteApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.UpdateApplicationUserRequest;

public interface UserService {
	
	DataResult<List<ApplicationUser>>getAll();
	
	Result add(CreateApplicationUserRequest createApplicationUserRequest );
	
	Result update(UpdateApplicationUserRequest updateApplicationUserRequest);
	
	Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest);
}
