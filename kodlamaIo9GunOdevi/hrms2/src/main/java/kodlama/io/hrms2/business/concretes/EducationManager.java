package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.EducationService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.EducationDao;
import kodlama.io.hrms2.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{
	
	private EducationDao educationDao;
	
	@Autowired
	public EducationManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}

	@Override
	public DataResult<List<Education>> getAll() {
		
		return new SuccesDataResult<List<Education>>(this.educationDao.findAll());
	}

	@Override
	public DataResult<Education> getById(int id) {
		
		return new SuccesDataResult<Education>(this.educationDao.getById(id), "id e gore getirildi.");
	}

	@Override
	public Result add(Education education) {
		
		this.educationDao.save(education);
		return new SuccesResult("Basariyla eklendi.");
	}

}
