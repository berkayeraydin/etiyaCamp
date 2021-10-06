package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.InvoiceDetailService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.InvoiceDetailDao;
import com.etiya.ReCapProject.entities.concretes.Invoice;
import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.dtos.InvoiceDetailDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteInvoiceDetailRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateInvoiceDetailRequest;

@Service
public class InvoiceDetailManager implements InvoiceDetailService {

	private InvoiceDetailDao invoiceDetailDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public InvoiceDetailManager(InvoiceDetailDao invoiceDetailDao, ModelMapperService modelMapperService) {
		super();
		this.invoiceDetailDao = invoiceDetailDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<InvoiceDetail>> getAll() {

		return new SuccessDataResult<List<InvoiceDetail>>(this.invoiceDetailDao.findAll(),
				Messages.InvoiceDetailsListed);
	}

	@Override
	public DataResult<InvoiceDetail> getById(int invoiceDetaillId) {

		return new SuccessDataResult<InvoiceDetail>(this.invoiceDetailDao.getById(invoiceDetaillId),
				Messages.InvoiceDetailListed);
	}

	@Override
	public DataResult<List<InvoiceDetailDetailDto>> getInvoiceDetailsDetail() {

		List<InvoiceDetail> invoiceDetails = this.invoiceDetailDao.findAll();

		List<InvoiceDetailDetailDto> invoiceDetailDetailDtos = invoiceDetails.stream()
				.map(invoiceDetail -> modelMapperService.forDto().map(invoiceDetail, InvoiceDetailDetailDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<InvoiceDetailDetailDto>>(invoiceDetailDetailDtos,
				Messages.InvoiceDetailsListed);
	}

	@Override
	public DataResult<InvoiceDetailDetailDto> getInvoiceDetailDetailById(int invoiceDetailId) {

		InvoiceDetail invoiceDetail = this.invoiceDetailDao.getById(invoiceDetailId);

		return new SuccessDataResult<InvoiceDetailDetailDto>(
				modelMapperService.forDto().map(invoiceDetail, InvoiceDetailDetailDto.class),
				Messages.InvoiceDetailListed);
	}

	@Override
	public DataResult<List<InvoiceDetailDetailDto>> getInvoiceDetailDetailsByInvoiceId(int invoiceId) {

		List<InvoiceDetail> invoiceDetails = this.invoiceDetailDao.getByInvoice_InvoiceId(invoiceId);

		List<InvoiceDetailDetailDto> invoiceDetailDetailDtos = invoiceDetails.stream()
				.map(invoiceDetail -> modelMapperService.forDto().map(invoiceDetail, InvoiceDetailDetailDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<InvoiceDetailDetailDto>>(invoiceDetailDetailDtos,
				Messages.InvoiceDetailsListed);
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

		return new SuccessResult(Messages.InvoiceDetailAdded);
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

		return new SuccessResult(Messages.InvoiceDetailDeleted);
	}

	// Faturaya ait detaların genel toplam tutarını hesaplar
	@Override
	public DataResult<Double> getSumtotalPriceByInvoice_InvoiceId(int invoiceId) {

		if (!this.invoiceDetailDao.existsByInvoice_InvoiceId(invoiceId)) {
			return new ErrorDataResult<Double>(0.0, "");
		}
		return new SuccessDataResult<Double>(this.invoiceDetailDao.SumTotalPriceByInvoice_InvoiceId(invoiceId));
	}

}
