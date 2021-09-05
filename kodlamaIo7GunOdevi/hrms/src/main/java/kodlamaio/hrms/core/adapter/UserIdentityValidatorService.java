package kodlamaio.hrms.core.adapter;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface UserIdentityValidatorService {
	
	boolean isValid(JobSeeker jobSeeker);
}
