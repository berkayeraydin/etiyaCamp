package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceDetailRequest;

public interface InvoiceDetailService {
	
	DataResult<List<InvoiceDetail>> getAll();

	DataResult<InvoiceDetail> getById(int invoiceDetaillId);
	
	DataResult<List<InvoiceDetail>> getInvoiceDetailsByInvoiceId(int invoiceId);
	
	DataResult<Double> getSumtotalPriceByInvoice_InvoiceId(int invoiceId);

	Result add(CreateInvoiceDetailRequest createInvoiceDetailRequests);

	Result update(UpdateInvoiceDetailRequest updateInvoiceDetailRequest);

	Result delete(DeleteInvoiceDetailRequest deleteInvoiceDetailRequest);
}
