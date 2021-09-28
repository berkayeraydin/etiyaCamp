package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.City;
import com.etiya.ReCapProject.entities.requests.create.CreateCityRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCityRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCityRequest;

@RestController
@RequestMapping("api/cities")
public class CitysController {
	
	private CityService cityService;
	
	@Autowired
	public CitysController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<City>> getAll() {
		return this.cityService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<City> getById(int cityId) {
		return this.cityService.getById(cityId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCityRequest createCityRequest) {
		return this.cityService.add(createCityRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCityRequest updateCityRequest) {
		return this.cityService.update(updateCityRequest);
	}
	
	@PostMapping("/delete")
	public Result delte(DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}
}
