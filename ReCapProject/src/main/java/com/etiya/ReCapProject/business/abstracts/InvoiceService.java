package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceRequest;

public interface InvoiceService {
	
	DataResult<List<Invoice>> getAll();

	DataResult<Invoice> getById(int invoiceId);

	// DataResult<InvoiceDetailDto> getRentalDetailsByRentalId(int rentalId);

	Result add(CreateInvoiceRequest createInvoiceRequest);

	Result update(UpdateInvoiceRequest updateInvoiceRequest);

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
	
	DataResult<List<Invoice>> getByRental_ApplicationUser_UserId(int userId);

	DataResult<List<Invoice>> getByCreationDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest);
}
