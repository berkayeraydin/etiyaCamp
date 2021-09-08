package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.UserService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.UserDao;
import kodlama.io.hrms2.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		
		return new SuccesDataResult<List<User>>(this.userDao.findAll(), "Basariyla user listelendi.");
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccesResult("Eklendi.");
	}

}
