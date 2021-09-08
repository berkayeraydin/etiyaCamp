package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.JobSeekerService;
import kodlama.io.hrms2.core.adapter.UserIdentityValidatorService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.ErrorResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.JobSeekersDao;
import kodlama.io.hrms2.entities.concretes.JobSeekers;

@Service
public class JobSeekerManager implements JobSeekerService{
	
	private JobSeekersDao jobSeekersDao;
	private UserIdentityValidatorService userIdentityValidatorService;

	@Autowired
	public JobSeekerManager(JobSeekersDao jobSeekersDao, UserIdentityValidatorService userIdentityValidatorService) {
		super();
		this.jobSeekersDao = jobSeekersDao;
		this.userIdentityValidatorService = userIdentityValidatorService;
	}

	@Override
	public DataResult<List<JobSeekers>> getAll() {
	
		return new SuccesDataResult<List<JobSeekers>>(this.jobSeekersDao.findAll(), "JobSeekers Basariyla Listelendi.");
	}

	@Override
	public Result add(JobSeekers jobSeekers) {
		if(userIdentityValidatorService.isValid(jobSeekers) == false) {
			return new ErrorResult("JobSeeker Eklenemedi. Mernis Dogrulanmadi.");
		}else if(approval(jobSeekers)==false) {
			return new ErrorResult("JobSeeker Eklenemedi. Bos Birakamazsiniz.");
		}
		this.jobSeekersDao.save(jobSeekers);
		return new SuccesResult("JobSeekers Basariyla Eklendi.");
	}
	
	public boolean checkFirstName(JobSeekers jobSeeker) {
		if(jobSeeker.getFirst_name()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkLastName(JobSeekers jobSeeker) {
		if(jobSeeker.getLast_name()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkIdentitiyNumber(JobSeekers jobSeeker) {
		if(jobSeeker.getNational_identity()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkBirthDate(JobSeekers jobSeeker) {
		if(jobSeeker.getBirth_date()== null) {
			return false;
		}
		return true;
	}
	
	public boolean checkUserId(JobSeekers jobSeeker) {
		if(jobSeeker.getUser().getId() == 0) {
			return false;
		}
		return true;
	}
	
	public boolean approval(JobSeekers jobSeeker) {
		if(checkFirstName(jobSeeker) && checkLastName(jobSeeker) && checkIdentitiyNumber(jobSeeker) 
				&& checkBirthDate(jobSeeker) && checkUserId(jobSeeker) == true) {
			return true;
		}
		return false;
	}
	
	
	
	
	public boolean checkIdentityVerify(JobSeekers jobSeeker) {
		List<JobSeekers> jobSeekers = this.jobSeekersDao.findAll();
		for (JobSeekers jobSeekerIndex : jobSeekers) {
			if ( jobSeeker.getNational_identity()== jobSeekerIndex.getNational_identity()) {
				return true;
			}
		}
		return false;
	}

}
