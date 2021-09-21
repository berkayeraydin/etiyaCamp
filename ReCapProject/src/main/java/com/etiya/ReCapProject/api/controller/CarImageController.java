package com.etiya.ReCapProject.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

@RestController
@RequestMapping("/api/carimages")
public class CarImageController {
	
	private CarImageService carImageService;
	
	@Autowired
	public CarImageController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarImage>> getAll(){
		
		return this.carImageService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add2(@RequestParam("carId") int carId, MultipartFile file) throws IOException {
		CreateCarImageRequest createCarImageRequest = new CreateCarImageRequest();
		createCarImageRequest.setCarId(carId);
		createCarImageRequest.setFile(file);
		return this.carImageService.add(createCarImageRequest);
	}
	
	@PostMapping("/update")
	public Result update(@RequestParam("carImageId") int carImageId,@RequestParam("file") MultipartFile file) throws IOException {
		
		UpdateCarImageRequest updateCarImageRequest = new UpdateCarImageRequest();
		updateCarImageRequest.setCarImageId(carImageId);
		updateCarImageRequest.setFile(file);
		
		return this.carImageService.update(updateCarImageRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		
		return this.carImageService.delete(deleteCarImageRequest);
		
	}
	
	@GetMapping("/getByCar_CarId")
	public DataResult<List<CarImage>> getByCar_CarId(int carId){
		
		return this.carImageService.getByCar_CarId(carId);
	}

	

	
}
