package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.LanguageJobSeekerService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.LanguageJobseekerDao;
import kodlama.io.hrms2.entities.concretes.LanguageJobseeker;

@Service
public class LanguageJobSeekerManager implements LanguageJobSeekerService {
	
	private LanguageJobseekerDao languageJobseekerDao;
	
	@Autowired
	public LanguageJobSeekerManager(LanguageJobseekerDao languageJobseekerDao) {
		super();
		this.languageJobseekerDao = languageJobseekerDao;
	}

	@Override
	public DataResult<List<LanguageJobseeker>> getAll() {

		return new SuccesDataResult<List<LanguageJobseeker>>(this.languageJobseekerDao.findAll());
	}

	@Override
	public Result add(LanguageJobseeker languageJobseeker) {
		
		this.languageJobseekerDao.save(languageJobseeker);
		return new SuccesResult("Basariyla eklendi.");
	}

}
