package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.create.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateApplicationUserRequest;

public interface UserService {
	
	DataResult<List<ApplicationUser>> getAll();
	
	DataResult<ApplicationUser> getById(int applicationUserId);

	Result add(CreateApplicationUserRequest createApplicationUserRequest);

	Result update(UpdateApplicationUserRequest updateApplicationUserRequest);

	Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest);
	
	DataResult<ApplicationUser> getByEmail(String email);
	
	Result existsByEmail(String email);
}
