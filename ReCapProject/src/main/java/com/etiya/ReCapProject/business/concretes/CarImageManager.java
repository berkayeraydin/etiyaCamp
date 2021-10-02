package com.etiya.ReCapProject.business.concretes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.abstracts.CarImageService;
import com.etiya.ReCapProject.business.constants.FilePathConfiguration;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
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

	@Autowired
	public CarImageManager(CarImageDao carImageDao) {
		super();
		this.carImageDao = carImageDao;
	}

	@Override
	public DataResult<List<CarImage>> getAll() {
		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), Messages.CarImagesListed);
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		var result = BusinessRules.run(checkCarImagesCount(createCarImageRequest.getCarId(), 5),
				checkImageType(createCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());

		CarImage carImage = new CarImage();
		carImage.setImagePath(this.createCarImagePathAndreturnCarImagePath(createCarImageRequest));
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
				checkImageType(updateCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		carImage.setImagePath(
				this.updateCarImagePathAndreturnCarImagePath(updateCarImageRequest, carImage.getImagePath()));
		carImage.setDate(dateNow);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated);
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		CarImage carImage = this.carImageDao.getById(deleteCarImageRequest.getCarImageId());

		this.carImageDao.delete(carImage);
		this.deleteCarImage(carImage.getImagePath());

		return new SuccessResult(Messages.CarImageDeleted);
	}

	@Override
	public DataResult<List<CarImage>> getCarImagesByCarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData(),
				Messages.CarImagesListed);
	}

	private Result checkCarImagesCount(int CarId, int limit) {
		if (this.carImageDao.countByCar_CarId(CarId) >= limit) {
			return new ErrorResult(Messages.CarImagesCountOfCarError);
		}
		return new SuccessResult();
	}

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

	private Result checkImageType(MultipartFile file) {
		if (this.checkImageIsNull(file).isSuccess() == false) {
			return new ErrorResult(this.checkImageIsNull(file).getMessage());
		}
		if (!file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpeg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("png")) {
			return new ErrorResult(Messages.CarImageTypeIsNotMatched);
		}
		return new SuccessResult();

	}

	private Result checkImageIsNull(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult(Messages.CarImageIsNotSelected);
		}
		return new SuccessResult();
	}

	private String createCarImagePathAndreturnCarImagePath(CreateCarImageRequest createCarImageRequest)
			throws IOException {

		String imageRandomName = java.util.UUID.randomUUID().toString();

		String mainPath = FilePathConfiguration.mainPath;
		String newCarFolder = "car" + createCarImageRequest.getCarId();
		String newImagePath = imageRandomName + "." + createCarImageRequest.getFile().getContentType().toString()
				.substring(createCarImageRequest.getFile().getContentType().indexOf("/") + 1);

		File myFolder = new File(mainPath + newCarFolder);
		myFolder.mkdir();

		File myFile = new File(mainPath + newCarFolder + "\\" + newImagePath);
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(createCarImageRequest.getFile().getBytes());
		fos.close();

		return newCarFolder + "\\" + newImagePath;
	}

	private String updateCarImagePathAndreturnCarImagePath(UpdateCarImageRequest updateCarImageRequest,
			String willBeDeletedCarImagePath) throws IOException {

		String mainPath = FilePathConfiguration.mainPath;
		String newCarFolder = willBeDeletedCarImagePath.substring(0, willBeDeletedCarImagePath.indexOf("\\"));

		this.deleteCarImage(willBeDeletedCarImagePath);

		String imageRandomName = java.util.UUID.randomUUID().toString();

		String newImagePath = imageRandomName + "." + updateCarImageRequest.getFile().getContentType().toString()
				.substring(updateCarImageRequest.getFile().getContentType().indexOf("/") + 1);

		File myFile = new File(mainPath + newCarFolder + "\\" + newImagePath);
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(updateCarImageRequest.getFile().getBytes());
		fos.close();

		return newCarFolder + "\\" + newImagePath;
	}

	private void deleteCarImage(String willBeDeletedCarImagePath) {

		String mainPath = FilePathConfiguration.mainPath;

		if (!willBeDeletedCarImagePath.isEmpty() && !willBeDeletedCarImagePath.isBlank()) {

			File willBeDeletedImage = new File(mainPath + willBeDeletedCarImagePath);
			willBeDeletedImage.delete();
		}
	}
}
