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
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.ErrorDataResult;
import com.etiya.ReCapProject.core.results.ErrorResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.ReCapProject.dataAccess.abstracts.CarImageDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CarImage;
import com.etiya.ReCapProject.entities.requests.CreateCarImageRequest;
import com.etiya.ReCapProject.entities.requests.DeleteCarImageRequest;
import com.etiya.ReCapProject.entities.requests.UpdateCarImageRequest;

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
	public Result add(CreateCarImageRequest createCarImageRequest, MultipartFile file) throws IOException {
		var result = BusinnesRules.run(checkCarImagesCount(createCarImageRequest.getCarId(), 5), checkImageIsNull(file),
				checkImageType(file));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		String imageRandomName = java.util.UUID.randomUUID().toString();

		File myFile = new File("C:\\Users\\berkay.eraydin\\Desktop\\img\\" + imageRandomName + ".jpg");
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();

		Car car = new Car();
		car.setCarId(createCarImageRequest.getCarId());

		CarImage carImage = new CarImage();
		carImage.setImagePath(myFile.toString());
		carImage.setDate(dateNow);

		carImage.setCar(car);

		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageAdded);
	}

	@Override
	public Result update(UpdateCarImageRequest updateCarImageRequest) {
		
		Date dateoperation = new java.sql.Date(new java.util.Date().getTime());
		String imageRandomName = java.util.UUID.randomUUID().toString();
		
		Car car = new Car();
		car.setCarId(updateCarImageRequest.getCarId());
	
		
		CarImage carImage = new  CarImage();
		carImage.setImagePath("carImages/" + imageRandomName + ".jpg");
		carImage.setCar(car);
		carImage.setCarImageId(updateCarImageRequest.getCarImageId());
	
		carImage.setDate(dateoperation);
		
		this.carImageDao.save(carImage);
		return new SuccessResult(Messages.CarImageUpdated) ;
	}

	@Override
	public Result delete(DeleteCarImageRequest deleteCarImageRequest) {
		
		CarImage carImages = new CarImage();
		carImages.setCarImageId(deleteCarImageRequest.getCarImageId());
		
		this.carImageDao.delete(carImages);
		return new SuccessResult(Messages.CarImageDeleted);
	}
	
	// Araba Id sine gore limit siniri resim ekleme
	private Result checkCarImagesCount(int carId,int limit) {
		
		if (this.carImageDao.countByCar_CarId(carId) >= limit) {
			return new ErrorResult(Messages.CarImagesCountOfCarError);
		}
		return new SuccessResult();
	}

	
	@Override
	public DataResult<List<CarImage>> getByCar_CarId(int carId) {

		return new SuccessDataResult<List<CarImage>>(returnCarImageWithDefaultImageIfCarImageIsNull(carId).getData(),
				Messages.CarImagesListed);
	}
	
	// Resmi olmayanlara otomatik atanan default resim
	private DataResult<List<CarImage>> returnCarImageWithDefaultImageIfCarImageIsNull(int carId) {

		if (this.carImageDao.existsByCar_CarId(carId)) {
			return new ErrorDataResult<List<CarImage>>(this.carImageDao.getByCar_CarId(carId));
		}

		List<CarImage> carImages = new ArrayList<CarImage>();
		CarImage carImage = new CarImage();
		carImage.setImagePath("C:\\Users\\berkay.eraydin\\Desktop\\img\\default.jpg");

		carImages.add(carImage);

		return new SuccessDataResult<List<CarImage>>(carImages, "Resimi olmayan Araba Default resim ile listelendi");

	}
	
	// Resmin tipini kontrol etme
	private Result checkImageType(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			return new ErrorResult();
		}
		if (file.getContentType().toString().substring(6) != "jpeg"
				&& file.getContentType().toString().substring(6) != "jpg"
				&& file.getContentType().toString().substring(6) != "png") {
			return new ErrorResult("Lütfen jpeg, jpg, png uzantılı resim seçiniz");
		}
		return new SuccessResult();

	}

	// Resim seçilmezse girilen bilgi mesaji
	private Result checkImageIsNull(MultipartFile file) {
		if (file == null) {
			return new ErrorResult("Herhangi bir resim seçmediniz");
		}
		return new SuccessResult();
	}

	
	
	

}
