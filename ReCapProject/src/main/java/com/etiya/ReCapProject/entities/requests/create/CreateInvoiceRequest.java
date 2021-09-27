package com.etiya.ReCapProject.entities.requests.create;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInvoiceRequest {
	
	
	@NotNull
	private int rentalId;
}
