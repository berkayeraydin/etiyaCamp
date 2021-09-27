package com.etiya.ReCapProject.api.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;

@RestController
@RequestMapping("api/ainvoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@PostMapping("/add")
	Result add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
		return this.invoiceService.add(createInvoiceRequest);
	}
	
	@GetMapping("/getall")
	public DataResult<List<Invoice>> getAll() {
		return this.invoiceService.getAll();
	}

	@GetMapping("/getByRental_ApplicationUser_UserId")
	public DataResult<List<Invoice>> getByRental_ApplicationUser_UserId(@RequestParam("userId") int userId) {
		return this.invoiceService.getByRental_ApplicationUser_UserId(userId);
	}

	@GetMapping("/getByCreationDateBetween")
	public DataResult<List<Invoice>> getByCreationDateBetween(String minDate, String maxDate) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate1 = dateFormat.parse(minDate);
		Date maxDate1 = dateFormat.parse(maxDate);

		InvoiceBetweenDateRequest invoiceBetweenDateRequest = new InvoiceBetweenDateRequest();
		invoiceBetweenDateRequest.setMinDate(minDate1);
		invoiceBetweenDateRequest.setMaxDate(maxDate1);

		return this.invoiceService.getByCreationDateBetween(invoiceBetweenDateRequest);
	}
}
