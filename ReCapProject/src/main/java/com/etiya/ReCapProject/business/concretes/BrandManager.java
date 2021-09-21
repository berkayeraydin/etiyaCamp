package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.requests.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.UpdateBrandRequest;

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

		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(), Messages.BrandsListed) ;
	}

	@Override
	public DataResult<Brand> getById(int brandId) {

		return new SuccessDataResult<Brand>(this.brandDao.getById(brandId),  Messages.BrandsListed) ;
	}

	@Override
	public Result add( CreateBrandRequest createBrandRequest) {
		Brand brand = new Brand();
		brand.setBrandName(createBrandRequest.getBrandName());
		
		this.brandDao.save(brand);
		
		return new SuccessResult(Messages.BrandAdded);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Brand brand = this.brandDao.getById(updateBrandRequest.getBrandId());
		brand.setBrandName(updateBrandRequest.getBrandName());
		
		this.brandDao.save(brand);	
		return new SuccessResult(Messages.BrandUpdated);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		
		Brand brand = this.brandDao.getById(deleteBrandRequest.getBrandId());
		
		this.brandDao.delete(brand);
		return new SuccessResult(Messages.BrandDeleted);
		
	}

}
