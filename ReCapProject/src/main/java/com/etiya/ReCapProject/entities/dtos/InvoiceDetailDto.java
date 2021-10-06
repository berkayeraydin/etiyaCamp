package com.etiya.ReCapProject.entities.dtos;

import java.util.Date;
import java.util.List;

import com.etiya.ReCapProject.entities.dtos.abstracts.CustomerDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailDto {

	private String invoiceNo;

	@JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date creationDate;

	private CustomerDto customerDto;

	private String carName;

	private String brandName;

	private String colorName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date rentDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnDate;

	private List<InvoiceDetailDetailDto> invoiceDetailDetailDtos;

	private double totalPrice;

}
