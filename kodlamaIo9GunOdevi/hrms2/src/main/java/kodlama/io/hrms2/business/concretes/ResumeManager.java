package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.ResumeService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.ResumeDao;
import kodlama.io.hrms2.entities.concretes.Resume;

@Service
public class ResumeManager implements ResumeService {
	
	ResumeDao resumeDao;
	
	@Autowired
	public ResumeManager(ResumeDao resumeDao) {
		super();
		this.resumeDao = resumeDao;
	}
	
	@Override
	public DataResult<List<Resume>> getAll() {
		
		return new SuccesDataResult<List<Resume>>(this.resumeDao.findAll());
	}

	@Override
	public DataResult<Resume> getById(int id) {
		
		return new SuccesDataResult<Resume>(this.resumeDao.getById(id), "id e gore getirildi.");
	}

	@Override
	public Result add(Resume resume) {
		
		this.resumeDao.save(resume);
		return new SuccesResult("Basariyla eklendi.");
	}

}
