package kodlama.io.hrms2.business.abstracts;

import java.util.List;

import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.Resume;

public interface ResumeService {
	
	DataResult<List<Resume>> getAll();
	
	DataResult<Resume> getById(int id);
	
	Result add(Resume resume);
}
