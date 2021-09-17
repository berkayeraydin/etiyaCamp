package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.CreateRentalRequest;

public interface RentalService {
	
	DataResult<List<Rental>>getAll();
	
	DataResult<Rental> getById(int colorId);
	
	Result add(CreateRentalRequest createRentalRequest);
	
	Result update(CreateRentalRequest createRentalRequest);
	
	Result delete(int rentalId);
}
