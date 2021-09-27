package com.etiya.ReCapProject.entities.requests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceBetweenDateRequest {

	@NotNull
	private Date minDate;

	@NotNull
	private Date maxDate;

}
