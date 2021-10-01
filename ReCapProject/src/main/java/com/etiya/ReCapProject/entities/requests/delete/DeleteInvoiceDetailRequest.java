package com.etiya.ReCapProject.entities.requests.delete;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteInvoiceDetailRequest {
	
	@NotNull
	private int invoiceDetailId;
}
