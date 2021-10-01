package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;
import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDto {
	private String invoiceNo;

	private Date creationDate;

	private CustomerDto customerDto;

	private String carName;

	private String brandName;

	private String colorName;

	private double dailyPrice;

	private Date rentDate;

	private Date returnDate;

	private List<InvoiceDetail> invoiceDetails;

	private double totalPrice;

}
