package com.etiya.ReCapProject.entities.requests.create;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.dtos.CardInformationDto;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateRentalRequest {
	
	@NotNull
	private Date rentDate;
	
	@NotNull
	private Date returnDate;
	
	boolean cardIsSaved;
	
	@NotNull
	private int carId;
	
	@NotNull
	private int userId;

	@NotNull
	private int returnCityId;
	
	@NotNull
	private List<RentalAdditional> rentalAdditionals;
	
	@NotNull
	@Valid
	private CardInformationDto cardInformationDto;
}
