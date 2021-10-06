package com.etiya.ReCapProject.business.concretes;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.AuthService;
import com.etiya.ReCapProject.business.abstracts.InvoiceDetailService;
import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
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
import com.etiya.ReCapProject.entities.dtos.InvoiceDetailDto;
import com.etiya.ReCapProject.entities.requests.InvoiceBetweenDateRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceRequest;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private InvoiceDetailService invoiceDetailService;
	private AuthService authService;
	private ModelMapperService modelMapperService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, InvoiceDetailService invoiceDetailService, AuthService authService,
			ModelMapperService modelMapperService) {
		super();
		this.invoiceDao = invoiceDao;
		this.invoiceDetailService = invoiceDetailService;
		this.authService = authService;
		this.modelMapperService = modelMapperService;
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
	public DataResult<InvoiceDetailDto> getInvoiceDetailByRentalId(int rentalId) {

		Invoice invoice = this.invoiceDao.getByRental_RentalId(rentalId);

		InvoiceDetailDto invoiceDetailDto = modelMapperService.forDto().map(invoice, InvoiceDetailDto.class);
		invoiceDetailDto.setCustomerDto(this.authService
				.getCustomerDtoByEmail(invoice.getRental().getApplicationUser().getEmail()).getData());
		invoiceDetailDto.setInvoiceDetailDetailDtos(
				this.invoiceDetailService.getInvoiceDetailDetailsByInvoiceId(invoice.getInvoiceId()).getData());
		invoiceDetailDto.setTotalPrice(
				this.invoiceDetailService.getSumtotalPriceByInvoice_InvoiceId(invoice.getInvoiceId()).getData());

		return new SuccessDataResult<InvoiceDetailDto>(invoiceDetailDto, Messages.InvoiceDetailsByRentalId);
	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getInvoiceDetailsByUserId(int userId) {

		List<Invoice> invoices = this.invoiceDao.getByRental_ApplicationUser_UserId(userId);

		List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<InvoiceDetailDto>();

		for (Invoice invoice : invoices) {

			InvoiceDetailDto invoiceDetailDto = modelMapperService.forDto().map(invoice, InvoiceDetailDto.class);

			invoiceDetailDto.setCustomerDto(this.authService
					.getCustomerDtoByEmail(invoice.getRental().getApplicationUser().getEmail()).getData());
			invoiceDetailDto.setInvoiceDetailDetailDtos(
					this.invoiceDetailService.getInvoiceDetailDetailsByInvoiceId(invoice.getInvoiceId()).getData());
			invoiceDetailDto.setTotalPrice(
					this.invoiceDetailService.getSumtotalPriceByInvoice_InvoiceId(invoice.getInvoiceId()).getData());

			invoiceDetailDtos.add(invoiceDetailDto);
		}

		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos, Messages.InvoicesListedByCustomer);
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {

		var result = BusinessRules.run(this.checkInvoiceByRentalId(createInvoiceRequest.getRental().getRentalId()));

		if (result != null) {
			return result;
		}

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());

		Rental rental = createInvoiceRequest.getRental();

		Invoice invoice = new Invoice();
		invoice.setCreationDate(dateNow);
		invoice.setInvoiceNo(this.returnInvoiceNo(dateNow).getData());
		invoice.setRental(rental);

		this.invoiceDao.save(invoice);

		invoice = this.invoiceDao.getByRental_RentalId(rental.getRentalId());

		CreateInvoiceDetailRequest createInvoiceDetailRequest = new CreateInvoiceDetailRequest();
		createInvoiceDetailRequest.setInvoiceId(invoice.getInvoiceId());
		createInvoiceDetailRequest.setRental(createInvoiceRequest.getRental());

		this.invoiceDetailService.add(createInvoiceDetailRequest);

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

	@Override
	public DataResult<List<InvoiceDetailDto>> getByCreationDateBetween(
			InvoiceBetweenDateRequest invoiceBetweenDateRequest) {

		List<Invoice> invoices = this.invoiceDao.getByCreationDateBetween(invoiceBetweenDateRequest.getMinDate(),
				invoiceBetweenDateRequest.getMaxDate());

		List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<InvoiceDetailDto>();

		for (Invoice invoice : invoices) {

			InvoiceDetailDto invoiceDetailDto = modelMapperService.forDto().map(invoice, InvoiceDetailDto.class);
			invoiceDetailDto.setCustomerDto(this.authService
					.getCustomerDtoByEmail(invoice.getRental().getApplicationUser().getEmail()).getData());

			invoiceDetailDtos.add(invoiceDetailDto);
		}

		return new SuccessDataResult<List<InvoiceDetailDto>>(invoiceDetailDtos, Messages.InvoicesListedByBetweenDate);
	}

	// Kiralama işlemine ait faturanın olup olmadığının kontrolünü yapar
	private Result checkInvoiceByRentalId(int rentalId) {

		if (this.invoiceDao.existsByRental_RentalId(rentalId)) {
			return new ErrorResult(Messages.InvoiceIsNotFoundByRental);
		}
		return new SuccessResult();
	}

	// Verilen tarih parametresine göre faturano olusturur
	private DataResult<String> returnInvoiceNo(Date dateNow) {

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

		return new SuccessDataResult<String>(invoiceNo, "");
	}

}
