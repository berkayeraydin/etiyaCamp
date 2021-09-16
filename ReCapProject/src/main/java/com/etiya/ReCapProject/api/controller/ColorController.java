package com.etiya.ReCapProject.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Color;

@RestController
@RequestMapping("/api/colors")
public class ColorController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Color>> getAll(){
		return this.colorService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Color> getById(int colorId) {
		return this.colorService.getById(colorId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Color color) {
		return this.colorService.add(color);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody Color color) {
		return this.colorService.update(color);
	}
	
	@PostMapping("/delete")
	public Result delete( @RequestBody Color color) {
		return this.colorService.delete(color);
	}
}
