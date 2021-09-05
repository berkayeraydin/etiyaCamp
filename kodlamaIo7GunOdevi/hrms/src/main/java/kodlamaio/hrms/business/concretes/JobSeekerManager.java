package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.core.adapter.UserIdentityValidatorService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccesDataResult;
import kodlamaio.hrms.core.utilities.results.SuccesResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {
	
	private JobSeekerDao jobSeekerDao;
	private UserIdentityValidatorService userIdentityValidatorService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao,UserIdentityValidatorService userIdentityValidatorService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.userIdentityValidatorService = userIdentityValidatorService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		
		return  new SuccesDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), "JobSeeker Basariyla Listelendi.");
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		if(userIdentityValidatorService.isValid(jobSeeker) == false) {
			return new ErrorResult("JobSeeker Eklenemedi. Mernis Dogrulanmadi.");
		}else if(approval(jobSeeker) == false){
			return new ErrorResult("JobSeeker Eklenemedi. Bos Birakamazsiniz.");
		}else if(checkEmailAndIdentityVerify(jobSeeker)==false) {
			return new ErrorResult("JobSeeker Eklenemedi. Email veya TC Kayitli. ");
		}
		this.jobSeekerDao.save(jobSeeker);
		return new SuccesResult("JobSeeker Basariyla Eklendi.");
	}
	
	public boolean checkFirstName(JobSeeker jobSeeker) {
		if(jobSeeker.getFirstName().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkLastName(JobSeeker jobSeeker) {
		if(jobSeeker.getLastName().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkIdentitiyNumber(JobSeeker jobSeeker) {
		if(jobSeeker.getIdentitiyNumber().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkBirthDate(JobSeeker jobSeeker) {
		if(jobSeeker.getBirthDate().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkEmail(JobSeeker jobSeeker) {
		if(jobSeeker.getEmail().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkPassword(JobSeeker jobSeeker) {
		if(jobSeeker.getPassword().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean checkPasswordRepeat(JobSeeker jobSeeker) {
		if(jobSeeker.getPasswordRepeat().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean approval(JobSeeker jobSeeker) {
		if(checkFirstName(jobSeeker) && checkLastName(jobSeeker) && checkIdentitiyNumber(jobSeeker) && checkEmail(jobSeeker) 
				&& checkBirthDate(jobSeeker) && checkPassword(jobSeeker)&& checkPasswordRepeat(jobSeeker) == true) {
			return true;
		}
		return false;
	}
	
	public boolean checkEmailAndIdentityVerify(JobSeeker jobSeeker) {
		List<JobSeeker> jobSeekers = this.jobSeekerDao.findAll();
		for (JobSeeker jobSeekerIndex : jobSeekers) {
			if ( jobSeeker.getEmail() == jobSeekerIndex.getEmail() || jobSeeker.getIdentitiyNumber()== jobSeekerIndex.getIdentitiyNumber()) {
				return false;
			}
		}
		return true;
	}
	
	

}
