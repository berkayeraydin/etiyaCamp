package com.etiya.ReCapProject.api.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.dtos.InvoiceDetailDto;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceRequest;

@RestController
@RequestMapping("api/ainvoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@GetMapping("/getall")
	public DataResult<List<Invoice>> getAll() {
		return this.invoiceService.getAll();
	}
//
//	@GetMapping("/getbyid")
//	public DataResult<Invoice> getById(@RequestParam("invoiceId") int invoiceId) {
//		return this.invoiceService.getById(invoiceId);
//	}

	@GetMapping("/getInvoiceDetailsByUserId")
	public DataResult<List<InvoiceDetailDto>> getInvoiceDetailsByUserId(@RequestParam("userId") int userId) {
		return this.invoiceService.getInvoiceDetailsByUserId(userId);
	}

	@GetMapping("/getInvoiceDetailByRentalId")
	public DataResult<InvoiceDetailDto> getInvoiceDetailByRentalId(@RequestParam("rentalId") int rentalId) {
		return this.invoiceService.getInvoiceDetailByRentalId(rentalId);
	}

	@GetMapping("/getByCreationDateBetween")
	public DataResult<List<InvoiceDetailDto>> getByCreationDateBetween(@RequestParam("minDate") String minDate,
			@RequestParam("maxDate") String maxDate) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate1 = dateFormat.parse(minDate);
		Date maxDate1 = dateFormat.parse(maxDate);

		InvoiceBetweenDateRequest invoiceBetweenDateRequest = new InvoiceBetweenDateRequest();
		invoiceBetweenDateRequest.setMinDate(minDate1);
		invoiceBetweenDateRequest.setMaxDate(maxDate1);

		return this.invoiceService.getByCreationDateBetween(invoiceBetweenDateRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
}