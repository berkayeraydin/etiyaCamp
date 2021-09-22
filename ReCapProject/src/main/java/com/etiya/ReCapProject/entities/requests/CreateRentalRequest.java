package com.etiya.ReCapProject.entities.requests;

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

public class CreateRentalRequest {
	
	@NotNull
	private Date rentDate;
	
	@NotNull
	private Date returnDate;
	
	@NotNull
	private int carId;
	
	@NotNull
	private int userId;
}
