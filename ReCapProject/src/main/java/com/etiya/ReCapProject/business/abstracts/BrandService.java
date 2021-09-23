package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.requests.create.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateBrandRequest;

public interface BrandService {
	
	DataResult<List<Brand>> getAll();
	
	DataResult<Brand> getById(int brandId);

	Result add(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);
	
	
}
