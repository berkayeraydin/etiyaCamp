package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class CarMaintenanceDetailDto {    
	
	private String description;   
	
	private Date maintenanceDate;   
	
	private Date returnDate;      
	
	private boolean isCarReturned;      
	
	private CarDetailDto carDetailDto; 
}
