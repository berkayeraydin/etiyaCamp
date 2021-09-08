package kodlama.io.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms2.business.abstracts.CityService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.core.utilities.results.SuccesDataResult;
import kodlama.io.hrms2.core.utilities.results.SuccesResult;
import kodlama.io.hrms2.dataAccess.abstracts.CityDao;
import kodlama.io.hrms2.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public DataResult<List<City>> getAll() {
		
		return new SuccesDataResult<List<City>>(this.cityDao.findAll(), "City Basariyla Listelendi.");
	}

	@Override
	public Result add(City city) {
		
		this.cityDao.save(city);
		return new SuccesResult("Basariyla Eklendi.");
	}

}
