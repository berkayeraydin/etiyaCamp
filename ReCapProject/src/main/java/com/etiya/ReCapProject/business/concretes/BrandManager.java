package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;

@Service
public class BrandManager  implements BrandService{
	
	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public DataResult<List<Brand>> getAll() {

		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(), "Basariyla Listelendi.") ;
	}

	@Override
	public DataResult<Brand> getById(int brandId) {

		return new SuccessDataResult<Brand>(this.brandDao.getById(brandId), "Brand Id e Gore Siralandi.") ;
	}

	@Override
	public Result add(Brand brand) {
		this.brandDao.save(brand);
		return new SuccessResult("Basariyla Eklendi.");
	}

	@Override
	public Result update(Brand brand) {
		this.brandDao.save(brand);	
		return new SuccessResult("Basariyla Guncellendi.");
	}

	@Override
	public Result delete(Brand brand) {

		this.brandDao.delete(brand);
		return new SuccessResult("Basariyla Silindi.");
		
	}

}
