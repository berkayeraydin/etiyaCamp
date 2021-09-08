package kodlama.io.hrms2.core.adapter;

import kodlama.io.hrms2.entities.concretes.JobSeekers;

public interface UserIdentityValidatorService {
	
	boolean isValid(JobSeekers jobSeekers);
}
