package com.etiya.ReCapProject.entities.requests.update;

import java.util.Date;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateRentalRequest {
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private Date rentDate;
	
	@NotNull
	private Date returnDate;
	
	private int carId;
}
