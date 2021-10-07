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

import com.etiya.ReCapProject.business.abstracts.ColorService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Color;
import com.etiya.ReCapProject.entities.dtos.ColorDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateColorRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteColorRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateColorRequest;

@RestController
@RequestMapping("api/colors")
public class ColorsController {
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {
		super();
		this.colorService = colorService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Color>> getAll() {
		return this.colorService.getAll();
	}
//
//	@GetMapping("/getbyid")
//	public DataResult<Color> getById(@RequestParam("colorId") int colorId) {
//		return this.colorService.getById(colorId);
//	}

	@GetMapping("/getColorsDetail")
	public DataResult<List<ColorDetailDto>> getColorsDetail() {
		return this.colorService.getColorsDetail();
	}

	@GetMapping("/getColorDetailById")
	public DataResult<ColorDetailDto> getColorDetailById(@RequestParam("colorId") int colorId) {
		return this.colorService.getColorDetailById(colorId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateColorRequest createColorRequest) {
		return this.colorService.add(createColorRequest);
	}

	@PutMapping("/update")
	public Result update(@Valid @RequestBody UpdateColorRequest updateColorRequest) {
		return this.colorService.update(updateColorRequest);
	}

	@DeleteMapping("/delete")
	public Result delte(@Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
}

