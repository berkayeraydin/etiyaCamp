package com.etiya.ReCapProject.entities.requests.create;


import com.etiya.ReCapProject.entities.concretes.Rental;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceDetailRequest {
	
	@NotNull
	private int invoiceId;

	@NotNull
	private Rental rental;
}
