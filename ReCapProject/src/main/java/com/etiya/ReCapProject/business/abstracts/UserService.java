package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.entities.concretes.User;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;

public interface UserService {
	
	DataResult<List<User>>getAll();
	
	Result add(User user);
	
	Result update(User user);
	
	Result delete(User user);
}
