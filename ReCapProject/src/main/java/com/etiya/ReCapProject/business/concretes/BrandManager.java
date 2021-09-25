package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.BrandDao;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.requests.create.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;

	@Autowired
	public BrandManager(BrandDao brandDao) {
		super();
		this.brandDao = brandDao;
	}

	@Override
	public DataResult<List<Brand>> getAll() {
		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(), Messages.BrandsListed);
	}

	@Override
	public DataResult<Brand> getById(int brandId) {
		return new SuccessDataResult<Brand>(this.brandDao.getById(brandId), Messages.BrandListed);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		var result = BusinessRules.run(this.checkBrandByBrandName(createBrandRequest.getBrandName()));

		if (result != null) {
			return result;
		}
		
		Brand brand = new Brand();
		brand.setBrandName(createBrandRequest.getBrandName());

		this.brandDao.save(brand);
		return new SuccessResult(Messages.BrandAdded);

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		var result = BusinessRules.run(this.checkBrandByBrandName(updateBrandRequest.getBrandName()));

		if (result != null) {
			return result;
		}
		
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
	
	private Result checkBrandByBrandName(String brandName) {
		if (this.brandDao.existsByBrandName(brandName)) {
			return new ErrorResult(Messages.BrandIsFount);
		}
		return new SuccessResult();
	}

}
