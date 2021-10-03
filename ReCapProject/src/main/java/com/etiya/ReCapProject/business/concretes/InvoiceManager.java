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
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDao;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;
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

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, InvoiceDetailService invoiceDetailService, AuthService authService) {
		super();
		this.invoiceDao = invoiceDao;
		this.invoiceDetailService = invoiceDetailService;
		this.authService = authService;
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

		InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
		invoiceDetailDto.setInvoiceNo(invoice.getInvoiceNo());
		invoiceDetailDto.setCreationDate(invoice.getCreationDate());

		Rental rental = invoice.getRental();
		invoiceDetailDto.setCustomerDto(
				this.authService.returnLoginedCustomerDto(rental.getApplicationUser().getEmail()).getData());
		invoiceDetailDto.setRentDate(rental.getRentDate());
		invoiceDetailDto.setReturnDate(rental.getReturnDate());

		Car car = rental.getCar();
		invoiceDetailDto.setCarName(car.getCarName());
		invoiceDetailDto.setBrandName(car.getBrand().getBrandName());
		invoiceDetailDto.setColorName(car.getColor().getColorName());
		invoiceDetailDto.setDailyPrice(car.getDailyPrice());

		List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
		invoiceDetailDto.setInvoiceDetails(invoiceDetail);

		invoiceDetailDto.setTotalPrice(
				this.invoiceDetailService.getSumtotalPriceByInvoice_InvoiceId(invoice.getInvoiceId()).getData());

		return new SuccessDataResult<InvoiceDetailDto>(invoiceDetailDto, "Kirama işleminin fatura detayı");
	}

	@Override
	public DataResult<List<InvoiceDetailDto>> getByRental_ApplicationUser_UserId(int userId) {

		List<Invoice> invoices = this.invoiceDao.getByRental_ApplicationUser_UserId(userId);

		List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<InvoiceDetailDto>();

		for (Invoice invoice : invoices) {

			InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();

			invoiceDetailDto.setInvoiceNo(invoice.getInvoiceNo());
			invoiceDetailDto.setCreationDate(invoice.getCreationDate());

			Rental rental = invoice.getRental();
			invoiceDetailDto.setCustomerDto(
					this.authService.returnLoginedCustomerDto(rental.getApplicationUser().getEmail()).getData());
			invoiceDetailDto.setRentDate(rental.getRentDate());
			invoiceDetailDto.setReturnDate(rental.getReturnDate());

			Car car = rental.getCar();
			invoiceDetailDto.setCarName(car.getCarName());
			invoiceDetailDto.setBrandName(car.getBrand().getBrandName());
			invoiceDetailDto.setColorName(car.getColor().getColorName());
			invoiceDetailDto.setDailyPrice(car.getDailyPrice());

			List<InvoiceDetail> invoiceDetail = invoice.getInvoiceDetails();
			invoiceDetailDto.setInvoiceDetails(invoiceDetail);

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
		invoice.setInvoiceNo(returnInvoiceNo(dateNow).getData());
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
	public DataResult<List<Invoice>> getByCreationDateBetween(InvoiceBetweenDateRequest invoiceBetweenDateRequest) {
		return new SuccessDataResult<List<Invoice>>(
				this.invoiceDao.getByCreationDateBetween(invoiceBetweenDateRequest.getMinDate(),
						invoiceBetweenDateRequest.getMaxDate()),
				Messages.InvoicesListedByBetweenDate);
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
