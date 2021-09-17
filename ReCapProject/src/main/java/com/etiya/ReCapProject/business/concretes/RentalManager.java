package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.ErrorResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.core.results.SuccessDataResult;
import com.etiya.ReCapProject.core.results.SuccessResult;
import com.etiya.ReCapProject.core.utilities.businnes.BusinnesRules;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	
	private RentalDao rentalDao;
	
	@Autowired
	public RentalManager(RentalDao rentalDao) {
		super();
		this.rentalDao = rentalDao;
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), "Basariyla Listelendi.");
	}

	@Override
	public Result add(Rental rental) {
		
		var result = BusinnesRules.run(checkCarIsReturned(rental.getCar().getCarId()));
				
		if (result != null) {
			return result;
		}
		
		
		this.rentalDao.save(rental);
		return new SuccessResult("Basariyla Eklendi.");
	}

	@Override
	public Result update(Rental rental) {
		this.rentalDao.save(rental);
		return new SuccessResult("Basariyla Guncellendi.");
	}

	@Override
	public Result delete(Rental rental) {
		this.rentalDao.delete(rental);
		return new SuccessResult("Basariyla Silindi.");
	}

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(rentalId), "Id e gore Listelendi.");
	}
	
	
	// Genel core da is olustur boolean degil Result don
	public Result checkCarIsReturned(int carId) {
		List<Rental> rentals = this.rentalDao.getByCar_CarId(carId);
		if ( rentals != null) {
			for (Rental rental : this.rentalDao.getByCar_CarId(carId)) {
				if(rental.getReturnDate() == null ) {
					// araba teslim edilmemiş. teslim tarihi null dur.
					return new ErrorResult("Teslim Tarihi yoktur. Teslim edilmemistir. Arac Kiralanamaz.");
				}
			}
		}
		
		return new SuccessResult();
	}
	

}