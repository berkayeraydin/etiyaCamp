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
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	private int carMaintenanceId;
	
	@NotNull
	private Date returnDate;
	
	@NotNull
	private String description;
}
