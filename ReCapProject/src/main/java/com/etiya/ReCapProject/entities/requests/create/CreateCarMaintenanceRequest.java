package com.etiya.ReCapProject.entities.requests.create;

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
public class CreateCarMaintenanceRequest {
	
	@NotNull
	private Date maintenanceDate;
	
	@NotNull
	private Date returnDate;
	
	@NotNull
	private int carId;
	
	@NotNull
	private String description;
	
	
}
