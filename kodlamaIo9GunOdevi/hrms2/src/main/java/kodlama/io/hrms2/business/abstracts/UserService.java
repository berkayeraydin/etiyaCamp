package kodlama.io.hrms2.business.abstracts;

import java.util.List;

import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.User;

public interface UserService {
	
	DataResult<List<User>> getAll();
	Result add(User user);
	
	//DataResult<User> getById(int id);
	
	//Result update(User user);
	//Result delete(int id);
}
