package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.City;
import com.etiya.ReCapProject.entities.dtos.CityDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCityRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCityRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCityRequest;

@RestController
@RequestMapping("api/cities")
public class CitiesController {
	
	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<City>> getAll() {
		return this.cityService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<City> getById(@RequestParam("cityId") int cityId) {
		return this.cityService.getById(cityId);
	}
	
	@GetMapping("/getCitysDetail")
	public DataResult<List<CityDetailDto>> getCitysDetail(){
		return this.cityService.getCitysDetail();
	}
	
	@GetMapping("/getCityDetailId")
	public DataResult<CityDetailDto> getCityDetailId(int cityId){
		return this.cityService.getCityDetailId(cityId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCityRequest createCityRequest) {
		return this.cityService.add(createCityRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCityRequest updateCityRequest) {
		return this.cityService.update(updateCityRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delte(DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}
}
