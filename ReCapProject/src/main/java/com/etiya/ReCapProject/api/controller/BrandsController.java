package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.BrandService;
import com.etiya.ReCapProject.core.utilities.result.*;
import com.etiya.ReCapProject.entities.concretes.Brand;
import com.etiya.ReCapProject.entities.dtos.BrandDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateBrandRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteBrandRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateBrandRequest;

@RestController
@RequestMapping("api/brands")
public class BrandsController {
	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Brand>> getAll() {
		return this.brandService.getAll();
	}
//
//	@GetMapping("/getbyid")
//	public DataResult<Brand> getById(@RequestParam("brandId") int brandId) {
//		return this.brandService.getById(brandId);
//	}

	@GetMapping("/getBrandsDetail")
	public DataResult<List<BrandDetailDto>> getBrandsDetail() {
		return this.brandService.getBrandsDetail();
	}

	@GetMapping("/getBrandDetailById")
	public DataResult<BrandDetailDto> getBrandDetailById(@RequestParam("brandId") int brandId) {
		return this.brandService.getBrandDetailById(brandId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@PutMapping("/update")
	public Result update(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
}
