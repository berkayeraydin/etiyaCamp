package com.etiya.ReCapProject.core.utilities.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.etiya.ReCapProject.business.constants.FilePathConfiguration;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;

@Service
public class ImageFileHelper implements FileHelper {

	private static String mainPath = FilePathConfiguration.mainPath;

	@Override
	public Result uploadImage(int carId, MultipartFile file) throws IOException {

		var result = BusinessRules.run(checkImageType(file));

		if (result != null) {
			return result;
		}

		String newCarFolderName = this.createCarImageFolderAndreturnCarImageFolderName(carId).getMessage();
		String newImageName = this.createImageName(file).getMessage();

		File myFile = new File(mainPath + newCarFolderName + "\\" + newImageName);
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();

		return new SuccessResult(newCarFolderName + "\\" + newImageName);
	}

	@Override
	public Result updateImage(MultipartFile file, String imagePath) throws IOException {

		var result = BusinessRules.run(checkImageType(file));

		if (result != null) {
			return result;
		}

		String CarFolderName = imagePath.substring(0, imagePath.indexOf("\\"));

		this.deleteImage(imagePath);

		String newImageName = this.createImageName(file).getMessage();

		File myFile = new File(mainPath + CarFolderName + "\\" + newImageName);
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();

		return new SuccessResult(CarFolderName + "\\" + newImageName);
	}

	@Override
	public Result deleteImage(String imagePath) {

		if (!imagePath.isEmpty() && !imagePath.isBlank()) {

			File willBeDeletedImage = new File(mainPath + imagePath);
			willBeDeletedImage.delete();
		}

		return new SuccessResult();
	}

	private Result checkImageIsNull(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return new ErrorResult(Messages.CarImageIsNotSelected);
		}
		return new SuccessResult();
	}

	// Dosya türünü kontrol eder
	private Result checkImageType(MultipartFile file) {
		if (!checkImageIsNull(file).isSuccess()) {
			return new ErrorResult(checkImageIsNull(file).getMessage());
		}
		if (!file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpeg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("jpg")
				&& !file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1).equals("png")) {
			return new ErrorResult(Messages.CarImageTypeIsNotMatched);
		}
		return new SuccessResult();

	}

	// Arabaya ait resim klasörü oluşturur ve geriye klasörün adını döndürür
	private Result createCarImageFolderAndreturnCarImageFolderName(int carId) {

		String newCarFolderName = "car" + carId;

		File myFolder = new File(mainPath + newCarFolderName);
		myFolder.mkdir();

		return new SuccessResult(newCarFolderName);
	}

	// Arabaya ait resme isim oluşturur.
	private Result createImageName(MultipartFile file) {
		String randomImageName = java.util.UUID.randomUUID().toString();

		String newImageName = randomImageName + "."
				+ file.getContentType().toString().substring(file.getContentType().indexOf("/") + 1);

		return new SuccessResult(newImageName);
	}
}
