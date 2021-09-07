package kodlama.io.hrms2.business.abstracts;

import java.util.List;

import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.JobSeekers;

public interface JobSeekerService {
	
	DataResult<List<JobSeekers>> getAll();
	Result add(JobSeekers jobSeekers);
}
