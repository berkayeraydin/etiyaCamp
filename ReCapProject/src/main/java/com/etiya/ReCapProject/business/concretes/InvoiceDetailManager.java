package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceDetailService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDetailDao;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceDetailRequest;

@Service
public class InvoiceDetailManager implements InvoiceDetailService {

	private InvoiceDetailDao invoiceDetailDao;

	@Autowired
	public InvoiceDetailManager(InvoiceDetailDao invoiceDetailDao) {
		super();
		this.invoiceDetailDao = invoiceDetailDao;
	}

	@Override
	public DataResult<List<InvoiceDetail>> getAll() {
		return new SuccessDataResult<List<InvoiceDetail>>(this.invoiceDetailDao.findAll(),
				"Faturaların detayları listelendi");
	}

	@Override
	public DataResult<InvoiceDetail> getById(int invoiceDetaillId) {
		return new SuccessDataResult<InvoiceDetail>(this.invoiceDetailDao.getById(invoiceDetaillId),
				"Fatura detayı listelendi");
	}

	@Override
	public DataResult<List<InvoiceDetail>> getInvoiceDetailsByInvoiceId(int invoiceId) {
		return new SuccessDataResult<List<InvoiceDetail>>(this.invoiceDetailDao.getByInvoice_InvoiceId(invoiceId),
				"Faturanın detayları listelendi");
	}

	@Override
	public Result add(CreateInvoiceDetailRequest createInvoiceDetailRequest) {

		Rental rental = createInvoiceDetailRequest.getRental();

		List<RentalAdditional> rentalAdditionals = rental.getRentalAdditionals();

		long totalRentalDay = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),
				rental.getReturnDate().toInstant());

		for (RentalAdditional rentalAdditional : rentalAdditionals) {

			InvoiceDetail invoiceDetail = new InvoiceDetail();

			if (rentalAdditional.getRentalAdditionalId() == 1) {
				invoiceDetail.setInvoiceDetailName(rentalAdditional.getRentalAdditionalName());
				invoiceDetail.setTotalPrice(rentalAdditional.getDailyPrice());

				Invoice invoice = new Invoice();
				invoice.setInvoiceId(createInvoiceDetailRequest.getInvoiceId());

				invoiceDetail.setInvoice(invoice);
			} else {

				invoiceDetail.setInvoiceDetailName(rentalAdditional.getRentalAdditionalName());
				invoiceDetail.setTotalPrice(rentalAdditional.getDailyPrice() * (int) totalRentalDay);

				Invoice invoice = new Invoice();
				invoice.setInvoiceId(createInvoiceDetailRequest.getInvoiceId());

				invoiceDetail.setInvoice(invoice);
			}
			this.invoiceDetailDao.save(invoiceDetail);
		}

		Invoice invoice = new Invoice();
		invoice.setInvoiceId(createInvoiceDetailRequest.getInvoiceId());

		InvoiceDetail invoiceDetail = new InvoiceDetail();
		invoiceDetail.setInvoiceDetailName("Araç kiralama hizmeti");
		invoiceDetail.setTotalPrice(rental.getCar().getDailyPrice() * (int) totalRentalDay);

		invoiceDetail.setInvoice(invoice);
		
		this.invoiceDetailDao.save(invoiceDetail);

		return new SuccessResult("Fatura detayı eklendi");
	}

	@Override
	public Result update(UpdateInvoiceDetailRequest updateInvoiceDetailRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteInvoiceDetailRequest deleteInvoiceDetailRequest) {

		InvoiceDetail invoiceDetail = this.invoiceDetailDao.getById(deleteInvoiceDetailRequest.getInvoiceDetailId());
		this.invoiceDetailDao.delete(invoiceDetail);

		return new SuccessResult("Fatura detayı silindi");
	}

	@Override
	public DataResult<Double> getSumtotalPriceByInvoice_InvoiceId(int invoiceId) {
		return new SuccessDataResult<Double>(this.invoiceDetailDao.SumTotalPriceByInvoice_InvoiceId(invoiceId));
	}

	
}
