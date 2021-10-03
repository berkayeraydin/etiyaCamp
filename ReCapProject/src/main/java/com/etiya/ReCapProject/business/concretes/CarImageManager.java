package com.etiya.ReCapProject.business.concretes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.constants.FilePathConfiguration;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.helpers.FileHelper;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.create.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private FileHelper fileHelper;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, FileHelper fileHelper) {
		super();
		this.carImageDao = carImageDao;
		this.fileHelper = fileHelper;
	}

	@Override
	public DataResult<List<CarImage>> getAll() {

		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), Messages.CarImagesListed);
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {

		var result = BusinessRules.run(checkCarImagesCount(createCarImageRequest.getCarId(), 5),
				this.fileHelper.uploadImage(createCarImageRequest.getCarId(), createCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());

		CarImage carImage = new CarImage();
		carImage.setImagePath(this.fileHelper
				.uploadImage(createCarImageRequest.getCarId(), createCarImageRequest.getFile()).getMessage());
		carImage.setDate(dateNow);

		carImage.setCar(car);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded);

	}

	@Override
	public DataResult<CarImage> getById(int carImageId) {

		return new SuccessDataResult<CarImage>(this.carImageDao.getById(carImageId));
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {

		CarImage carImage = this.carImageDao.getById(updateCarImageRequest.getCarImageId());

		var result = BusinessRules.run(checkCarImagesCount(carImage.getCar().getCarId(), 5),
				this.fileHelper.updateImage(updateCarImageRequest.getFile(), carImage.getImagePath()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		carImage.setImagePath(
				this.fileHelper.updateImage(updateCarImageRequest.getFile(), carImage.getImagePath()).getMessage());
		carImage.setDate(dateNow);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated);
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {

		CarImage carImage = this.carImageDao.getById(deleteCarImageRequest.getCarImageId());

		this.carImageDao.delete(carImage);

		this.fileHelper.deleteImage(carImage.getImagePath());

		return new SuccessResult(Messages.CarImageDeleted);
	}

	@Override
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData(),
				Messages.CarImagesListed);
	}

	// Arabaya ait resim sayısını kontrol eder
	private Result checkCarImagesCount(int CarId, int limit) {
		if (this.carImageDao.countByCar_CarId(CarId) >= limit) {
			return new ErrorResult(Messages.CarImagesCountOfCarError);
		}
		return new SuccessResult();
	}

	// Arabaya ait resim yoksa varsayılan resmi döndürür
	private DataResult<List<CarImage>> returnCarImageWithDefaultImageIfCarImageIsNull(int carId) {

		if (this.carImageDao.existsByCar_CarId(carId)) {
			return new ErrorDataResult<List<CarImage>>(this.carImageDao.getByCar_CarId(carId));
		}

		List<CarImage> carImages = new ArrayList<CarImage>();
		CarImage carImage = new CarImage();
		carImage.setImagePath(FilePathConfiguration.mainPath + FilePathConfiguration.defaultImageName);

		carImages.add(carImage);

		return new SuccessDataResult<List<CarImage>>(carImages, Messages.CarImagesListed);

	}

}
