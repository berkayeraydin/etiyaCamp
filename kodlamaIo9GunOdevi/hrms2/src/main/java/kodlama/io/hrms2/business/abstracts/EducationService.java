package kodlama.io.hrms2.business.abstracts;

import java.util.List;

import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.Education;

public interface EducationService {
		
	DataResult<List<Education>> getAll();
	
	DataResult<Education> getById(int id);
	
	Result add(Education education);
}
