package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerService {
	
	DataResult<List<JobSeeker>> getAll();
	Result add(JobSeeker jobSeeker);
	boolean checkFirstName(JobSeeker jobSeeker);
	boolean checkLastName(JobSeeker jobSeeker);
	boolean checkIdentitiyNumber(JobSeeker jobSeeker);
	boolean checkBirthDate(JobSeeker jobSeeker);
	boolean checkEmail(JobSeeker jobSeeker);
	boolean checkPassword(JobSeeker jobSeeker);
	boolean checkPasswordRepeat(JobSeeker jobSeeker);
	boolean approval(JobSeeker jobSeeker);
}
