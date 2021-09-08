package kodlama.io.hrms2.business.abstracts;

import java.util.List;

import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.JobAdvertisement;

public interface JobAdvertisementService {
	
	DataResult<List<JobAdvertisement>> getAll();
	Result add(JobAdvertisement jobAdvertisement);
	
	DataResult<List<JobAdvertisement>> getByEmployerId(int id);
	
	DataResult<List<JobAdvertisement>> getByActiveStatus();
	
	DataResult<List<JobAdvertisement>> getByActiveAndEmployerId(int employerId);
	
	DataResult<List<JobAdvertisement>> getByAscDate();
	
	DataResult<List<JobAdvertisement>> getByCompanyNameAndActive(String companyName);
	
	DataResult<List<JobAdvertisement>> getByCompanyName(String companyName);
}
