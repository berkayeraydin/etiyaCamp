package com.etiya.ReCapProject.business.concretes;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceRequest;

@Service
public class InvoiceManager implements InvoiceService{

	private InvoiceDao invoiceDao;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao) {
		super();
		this.invoiceDao = invoiceDao;
	}

	@Override
	public DataResult<List<Invoice>> getAll() {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.findAll(), Messages.InvoicesListed);
	}

	@Override
	public DataResult<Invoice> getById(int invoiceId) {
		return new SuccessDataResult<Invoice>(this.invoiceDao.getById(invoiceId), Messages.InvoiceListed);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {

		var result = BusinessRules.run(this.checkInvoiceByRentalId(createInvoiceRequest.getRentalId()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		String dateYear = dateFormat.format(dateNow);

		String lastInvoiceDate = "0000";
		String lastInvoiceNo = "REV0000000000000";

		if (this.invoiceDao.getTop1ByOrderByCreationDateDesc() != null) {
			lastInvoiceDate = dateFormat.format(this.invoiceDao.getTop1ByOrderByCreationDateDesc().getCreationDate());
			lastInvoiceNo = this.invoiceDao.getTop1ByOrderByCreationDateDesc().getInvoiceNo();
		}

		int lastInvoiceOreder = Integer.parseInt(lastInvoiceNo.substring(7));

		NumberFormat numberFormat = new DecimalFormat("000000000");

		int newInvoiceOrder = lastInvoiceOreder + 1;

		if (!dateYear.equals(lastInvoiceDate)) {
			newInvoiceOrder = 000000001;
		}

		String invoiceNo = "REV" + dateYear + numberFormat.format(newInvoiceOrder);

		Rental rental = new Rental();
		rental.setRentalId(createInvoiceRequest.getRentalId());

		Invoice invoice = new Invoice();
		invoice.setCreationDate(dateNow);
		invoice.setInvoiceNo(invoiceNo);
		invoice.setRental(rental);

		this.invoiceDao.save(invoice);

		return new SuccessResult(Messages.InvoiceAdded);
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {

		Invoice invoice = this.invoiceDao.getById(deleteInvoiceRequest.getInvoiceId());
		this.invoiceDao.delete(invoice);

		return new SuccessResult(Messages.InvoiceDeleted);
	}

	private Result checkInvoiceByRentalId(int rentalId) {
		if (this.invoiceDao.existsByRental_RentalId(rentalId)) {
			return new ErrorResult(Messages.InvoiceIsNotFoundByRental);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Invoice>> getByRental_ApplicationUser_UserId(int userId) {
		return new SuccessDataResult<List<Invoice>>(this.invoiceDao.getByRental_ApplicationUser_UserId(userId),
				Messages.InvoicesListedByCustomer);
	}

	@Override
	public DataResult<List<Invoice>> getByCreationDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest) {
		return new SuccessDataResult<List<Invoice>>(
				this.invoiceDao.getByCreationDateBetween(invoiceBetweenDateRequest.getMinDate(),
						invoiceBetweenDateRequest.getMaxDate()),
				Messages.InvoicesListedByBetweenDate);
	}

}
