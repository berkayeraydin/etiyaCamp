package com.etiya.ReCapProject.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.dtos.CarImageDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarImageRequest;

@RestController
@RequestMapping("api/carimages")
public class CarImagesController {
	private CarImageService carImageService;

	@Autowired
	public CarImagesController(CarImageService carImageService) {
		super();
		this.carImageService = carImageService;
	}

	@GetMapping("/getAll")
	public DataResult<List<CarImage>> getAll() {
		return this.carImageService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<CarImage> getById(int carImageId){
		return this.carImageService.getById(carImageId);
	}

//	@GetMapping("/getbycarid")
//	public DataResult<List<CarImage>> getByCar_CarId(@RequestParam("carId") int carId) {
//		return this.carImageService.getCarImagesByCarId(carId);
//	}
	
	@GetMapping("/getCarImagesDetail")
	public DataResult<List<CarImageDetailDto>> getCarImagesDetail() {
		return this.carImageService.getCarImagesDetail();
	}
	
	@GetMapping("/getCarImageDetailId")
	public DataResult<CarImageDetailDto> getCarImageDetailId(int carImageId){
		return this.carImageService.getCarImageDetailById(carImageId);
	}

	@PostMapping("/add")
	public Result add(@RequestParam("carId") int carId, MultipartFile file) throws IOException {

		CreateCarImageRequest createCarImageRequest = new CreateCarImageRequest();
		createCarImageRequest.setCarId(carId);
		createCarImageRequest.setFile(file);

		return this.carImageService.add(createCarImageRequest);
	}

	@PostMapping("/update")
	public Result update(@RequestParam("carImageId") int carImageId, @RequestParam("file")  MultipartFile file)
			throws IOException {

		UpdateCarImageRequest updateCarImageRequest = new UpdateCarImageRequest();
		updateCarImageRequest.setCarImageId(carImageId);
		updateCarImageRequest.setFile(file);

		return this.carImageService.update(updateCarImageRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		return this.carImageService.delete(deleteCarImageRequest);
	}
}