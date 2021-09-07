package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.EmployerService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.ErrorResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.EmployerDao;
import kodlama.io.hrms2.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{
	
	private EmployerDao employerDao;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccesDataResult<List<Employer>>(this.employerDao.findAll(), "Basariyla Listelendi.");
	}

	@Override
	public Result add(Employer employer) {
		if(approval(employer)==false) {
			return  new ErrorResult("Employer Eklenemedi. Bos Birakamazsiniz. ");
		}
		this.employerDao.save(employer);
		return new SuccesResult("Employer Basariyla eklendi.");
	}
	
	
	public boolean checkCompanyName(Employer employer) {
		if(employer.getCompanyName()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkWebSite(Employer employer) {
		if(employer.getWebsite()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkPhoneNumber(Employer employer) {
		if(employer.getPhoneNumber() == null) {
			return false;
		}
		return true;
	}
	
	public boolean checkUserId(Employer employer) {
		if(employer.getUser().getId()== 0 ) {
			return false;
		}
		return true;
	}
	
	public boolean approval(Employer employer) {
		if(checkCompanyName(employer) && checkPhoneNumber(employer)&& checkWebSite(employer)
				&& checkUserId(employer) && checkUserId(employer)== true) {
			return true;
		}
		return false;
	}
	
	public boolean checkEmailVerify(Employer employer) {
		List<Employer> employers = this.employerDao.findAll();
		for (Employer employerIndex : employers) {
			if(employer.getUser().getEmail() == employerIndex.getUser().getEmail()) {
				return false;
			}
		}
		return true;
	}

}
