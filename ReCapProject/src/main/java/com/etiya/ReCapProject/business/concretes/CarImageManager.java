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
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.constants.FilePathConfiguration;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.ErrorDataResult;
import com.etiya.ReCapProject.core.results.ErrorResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.ReCapProject.dataAccess.abstracts.CarDao;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

@Service
public class CarImageManager implements CarImageService {

	private CarImageDao carImageDao;
	private CarDao carDao;

	@Autowired
	public CarImageManager(CarImageDao carImageDao, CarDao carDao) {
		super();
		this.carImageDao = carImageDao;
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<CarImage>> getAll() {

		return new SuccessDataResult<List<CarImage>>(this.carImageDao.findAll(), Messages.CarImagesListed);
	}

	@Override
	public DataResult<CarImage> getById(int carImageId) {
		// TODO Auto-generated method stub
		return new SuccessDataResult<CarImage>(this.carImageDao.getById(carImageId), Messages.CarImagesListed);
	}

	@Override
	public Result add(CreateCarImageRequest createCarImageRequest) throws IOException {
		var result = BusinnesRules.run(checkCarImagesCount(createCarImageRequest.getCarId(), 5),
				checkImageType(createCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		Car car = this.carDao.getById(createCarImageRequest.getCarId());

		CarImage carImage = new CarImage();
		carImage.setImagePath(createCarImagePathReturnCarImagePath(createCarImageRequest));
		carImage.setDate(dateNow);

		carImage.setCar(car);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) throws IOException {
		CarImage carImage = this.carImageDao.getById(updateCarImageRequest.getCarImageId());

		var result = BusinnesRules.run(checkCarImagesCount(carImage.getCarImageId(), 5),
				checkImageType(updateCarImageRequest.getFile()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		carImage.setImagePath(
				this.updateCarImagePathReturnCarImagePath(updateCarImageRequest, carImage.getImagePath()));
		carImage.setDate(dateNow);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated);
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {

		CarImage carImages = this.carImageDao.getById(deleteCarImageRequest.getCarImageId());

		carImages.setCarImageId(deleteCarImageRequest.getCarImageId());

		this.carImageDao.delete(carImages);
		this.deleteCarImage(carImages.getImagePath());

		return new SuccessResult(Messages.CarImageDeleted);
	}

	@Override
	public DataResult<List<CarImage>> getByCar_CarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData(),
				Messages.CarImagesListed);
	}
	
	
	

	// Araba Id sine gore limit siniri resim ekleme
	private Result checkCarImagesCount(int carId, int limit) {

		if (this.carImageDao.countByCar_CarId(carId) >= limit) {
			return new ErrorResult(Messages.CarImagesCountOfCarError);
		}
		return new SuccessResult();
	}

	// Resmi olmayanlara otomatik atanan default resim
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

	// Resmin tipini kontrol etme
	private Result checkImageType(MultipartFile file) {

		if (this.checkImageIsNull(file).isSuccess() == false) {
			return this.checkImageIsNull(file);
		}
		if (!file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("jpeg")
				&& !file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().substring(file.getContentType().indexOf("/") + 1).equals("png")) {
			return new ErrorResult(Messages.CarImagesTypeIsNotMatched);
		}
		return new SuccessResult();

	}

	// Resim seçilmezse girilen bilgi mesaji
	private Result checkImageIsNull(MultipartFile file) {
		if (file == null) {
			return new ErrorResult(Messages.CarImageIsNotSlected);
		}
		return new SuccessResult();
	}

	// gelen resmi araba id sine gore klasorleyip ekliyor.
	private String createCarImagePathReturnCarImagePath(CreateCarImageRequest createCarImageRequest)
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

	// gelen resmi araba id sine gore guncelliyor ekliyor.
	private String updateCarImagePathReturnCarImagePath(UpdateCarImageRequest updateCarImageRequest,
			String willBeDeletedCarImagePath) throws IOException {

		String mainPath = FilePathConfiguration.mainPath;
		// String newCarFolder = "car" + updateCarImageRequest.getCarId();
		
		String newCarFolder = willBeDeletedCarImagePath.substring(0, willBeDeletedCarImagePath.indexOf("\\"));
		if (!willBeDeletedCarImagePath.isEmpty() && !willBeDeletedCarImagePath.isBlank()) {
			File willBeDeletedImage = new File(mainPath + willBeDeletedCarImagePath);
			willBeDeletedImage.delete();

		}

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
	
	private void deleteCarImage(String willBeDeletedCarImagePath)  {

		String mainPath = FilePathConfiguration.mainPath;
		// String newCarFolder = "car" + updateCarImageRequest.getCarId();

		if (!willBeDeletedCarImagePath.isEmpty() && !willBeDeletedCarImagePath.isBlank()) {
			
			File willBeDeletedImage = new File(mainPath + willBeDeletedCarImagePath);
			willBeDeletedImage.delete();

		}

	}

}
