package com.etiya.ReCapProject.entities.requests.create;

import com.etiya.ReCapProject.entities.concretes.Rental;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

	@NotNull
	private Rental rental;

}