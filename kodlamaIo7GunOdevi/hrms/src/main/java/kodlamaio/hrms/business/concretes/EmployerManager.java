package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccesDataResult;
import kodlamaio.hrms.core.utilities.results.SuccesResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	
	private  EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccesDataResult<List<Employer>>(this.employerDao.findAll(), "Employer Basariyla Listelendi.");
	}

	@Override
	public Result add(Employer employer) {
		if(approval(employer)== false) {
			return  new ErrorResult("Employer Eklenemedi. Bos Birakamazsiniz. ");
		}else if(checkEmailVerify(employer) == true) {
			return  new ErrorResult("Employer Eklenemedi. Email Kayitli. ");
		}
		this.employerDao.save(employer);
		return new SuccesResult("Employer Basariyla Eklendi.");
	}
	
	
	public boolean checkCompanyName(Employer employer) {
		if(employer.getCompanyName().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkWebAdress(Employer employer) {
		if(employer.getWebAdress().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkCompanyMail(Employer employer) {
		if(employer.getEmail().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkPhoneNumber(Employer employer) {
		if(employer.getPhoneNumber().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkPassword(Employer employer) {
		if(employer.getPassword().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkPasswordRepeat(Employer employer) {
		if(employer.getPasswordRepeat().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkEmail(Employer employer) {
		if(employer.getPassword().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean approval(Employer employer) {
		if(checkCompanyName(employer) && checkWebAdress(employer) && checkCompanyMail(employer) && checkPassword(employer) && checkPasswordRepeat(employer) 
				&& checkPhoneNumber(employer)&& checkEmail(employer) == true) {
			return true;
		}
		return false;
	}
	
	public boolean checkEmailVerify(Employer employer) {
		List<Employer> employers = this.employerDao.findAll();
		for (Employer employerIndex : employers) {
			if(employer.getEmail() == employerIndex.getEmail()) {
				return false;
			}
		}
		return true;
	}
	
}
