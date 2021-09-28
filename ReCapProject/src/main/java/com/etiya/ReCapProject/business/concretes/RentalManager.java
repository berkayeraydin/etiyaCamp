package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.adapters.CustomerFindeksScoreService;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorDataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.City;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.dtos.CardInformationDto;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateInvoiceRequest;
import com.etiya.ReCapProject.entities.requests.create.CreateRentalRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteRentalRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarService carService;
	private UserService userService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private CustomerFindeksScoreService customerFindeksScoreService;
	private CardInformationService cardInformationService;
	private InvoiceService invoiceService;
	private CityService cityService;

	@Autowired
	public RentalManager(RentalDao rentalDao, CarService carService, UserService userService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			CustomerFindeksScoreService customerFindeksScoreService, CardInformationService cardInformationService,
			InvoiceService invoiceService, CityService cityService) {
		super();
		this.rentalDao = rentalDao;
		this.carService = carService;
		this.userService = userService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.customerFindeksScoreService = customerFindeksScoreService;
		this.cardInformationService = cardInformationService;
		this.invoiceService = invoiceService;
		this.cityService = cityService;
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), Messages.RentalsListed);
	}

	@Override
	public DataResult<Rental> getById(int rentalId) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(rentalId), Messages.RentalListed);
	}

	@Override
	public DataResult<RentalDetailDto> getRentalDetailsByRentalId(int rentalId) {

		Rental rental = this.rentalDao.getById(rentalId);

		RentalDetailDto rentalDetailDto = new RentalDetailDto();
		CarDetailDto carDetailDto = this.carService.getCarDetailsByCarId(rental.getCar().getCarId()).getData();

		if (this.individualCustomerService.existsByUserId(rental.getApplicationUser().getUserId()).isSuccess()) {

			IndividualCustomerDetailDto individualCustomerDetailDto = this.individualCustomerService
					.getIndividualCustomerDetailsById(
							rental.getApplicationUser().getIndividualCustomer().getIndividualCustomerId())
					.getData();

			rentalDetailDto.setRentDate(rental.getRentDate());
			rentalDetailDto.setReturnDate(rental.getReturnDate());
			rentalDetailDto.setCarDetailDto(carDetailDto);
			rentalDetailDto.setCustomerDto(individualCustomerDetailDto);

			return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, Messages.RentalDetail);
		}
		if (this.corporateCustomerService.existsByUserId(rental.getApplicationUser().getUserId()).isSuccess()) {

			CorporateCustomerDetailDto corporateCustomerDetailDto = this.corporateCustomerService
					.getCorporateCustomerDetailsById(
							rental.getApplicationUser().getCorporateCustomer().getCorporateCustomerId())
					.getData();

			rentalDetailDto.setRentDate(rental.getRentDate());
			rentalDetailDto.setReturnDate(rental.getReturnDate());
			rentalDetailDto.setCarDetailDto(carDetailDto);
			rentalDetailDto.setCustomerDto(corporateCustomerDetailDto);

			return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, Messages.RentalDetail);
		}

		return new ErrorDataResult<RentalDetailDto>();
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		var result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()),
				checkCustomerFindeksScore(createRentalRequest.getUserId(), createRentalRequest.getCarId()),
				this.cardInformationService
						.checkCardFormat(createRentalRequest.getCardInformationDto().getCardNumber()),
				this.carService.checkCarIsInGallery(createRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();
		this.carService.carListedIsFalse(car.getCarId());

		ApplicationUser applicationUser = this.userService.getById(createRentalRequest.getUserId()).getData();

		City takeCity = car.getCity();

		City returnCity = this.cityService.getById(createRentalRequest.getReturnCityId()).getData();

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setRentKilometer(car.getKilometer());

		rental.setCar(car);
		rental.setApplicationUser(applicationUser);
		rental.setTakeCity(takeCity);
		rental.setReturnCity(returnCity);

		this.rentalDao.save(rental);

		if (createRentalRequest.isCardIsSaved()) {
			this.cardInformationSavedIfCardIsSavedIsTrue(createRentalRequest.getCardInformationDto(),
					createRentalRequest.getUserId());
		}

		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		createInvoiceRequest.setRentalId(this.rentalDao
				.getTop1RentalByApplicationUser_UserIdOrderByRentDateDesc(applicationUser.getUserId()).getRentalId());

		this.invoiceService.add(createInvoiceRequest);

		return new SuccessResult(Messages.RentalAdded);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		var result = BusinessRules.run(checkCustomerFindeksScore(
				this.rentalDao.getById(updateRentalRequest.getRentalId()).getApplicationUser().getUserId(),
				updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(updateRentalRequest.getCarId()).getData();

		City takeCity = car.getCity();

		City returnCity = this.cityService.getById(updateRentalRequest.getReturnCityId()).getData();

		Rental rental = this.rentalDao.getById(updateRentalRequest.getRentalId());

		if (this.checkCarIsReturned(updateRentalRequest.getCarId()).isSuccess()) {
			rental.setRentDate(updateRentalRequest.getRentDate());
		}

		rental.setReturnDate(updateRentalRequest.getReturnDate());

		if (rental.getCar().getCarId() != car.getCarId()) {

			this.carService.carListedIsTrue(rental.getCar().getCarId());

			this.carService.carListedIsFalse(car.getCarId());
			rental.setCar(car);
			rental.setTakeCity(takeCity);
			rental.setRentKilometer(car.getKilometer());
		}

		rental.setReturnCity(returnCity);

		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {

		Rental rental = this.rentalDao.getById(deleteRentalRequest.getRentalId());

		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RentalDeleted);
	}

	@Override
	public Result carAtRentalReturnedIsTrue(CarReturnedRequest carReturnedRequest) {

		Rental rental = this.rentalDao.getById(carReturnedRequest.getRentalId());
		rental.setCarReturned(true);
		rental.setReturnKilometer(carReturnedRequest.getReturnKilometer());

		Car car = this
				.carCityUpdateIfReturnedDifferentCity(rental.getCar().getCarId(), rental.getReturnCity().getCityId())
				.getData();

		this.carService.carListedIsTrue(car.getCarId());
		car.setKilometer(rental.getReturnKilometer());

		rental.setCar(car);

		this.rentalDao.save(rental);

		return new SuccessResult(Messages.RentalCarIsReturned);
	}

	private Result checkCarIsReturned(int carId) {
		if (this.rentalDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.RentalCarNotReturn);
		}
		return new SuccessResult();
	}

	private DataResult<Car> carCityUpdateIfReturnedDifferentCity(int carId, int cityId) {

		Car car = this.carService.getById(carId).getData();

		if (car.getCity().getCityId() != cityId) {

			City city = this.cityService.getById(cityId).getData();
			car.setCity(city);
		}

		return new SuccessDataResult<Car>(car);
	}

	private Result checkCustomerFindeksScore(int applicationUserId, int carId) {

		if (this.individualCustomerService.existsByUserId(applicationUserId).isSuccess()) {
			IndividualCustomer individualCustomer = this.individualCustomerService
					.getByApplicationUser_UserId(applicationUserId).getData();

			if (this.carService.getById(carId).getData().getMinFindeksScore() > this.customerFindeksScoreService
					.getIndividualFindeksScore(individualCustomer.getNationalIdentityNumber())) {

				return new ErrorResult(Messages.FindeksScoreIsInsufficient);
			}

		}

		if (this.corporateCustomerService.existsByUserId(applicationUserId).isSuccess()) {

			CorporateCustomer corporateCustomer = this.corporateCustomerService
					.getByApplicationUser_UserId(applicationUserId).getData();

			if (this.carService.getById(carId).getData().getMinFindeksScore() > this.customerFindeksScoreService
					.getCorporateFindeksScore(corporateCustomer.getTaxNumber())) {

				return new ErrorResult(Messages.FindeksScoreIsInsufficient);
			}
		}

		return new SuccessResult();

	}

	private Result cardInformationSavedIfCardIsSavedIsTrue(CardInformationDto cardInformationDto, int UserId) {

		CreateCardInformationRequest cardInformationRequest = new CreateCardInformationRequest();
		cardInformationRequest.setCardName(cardInformationDto.getCardName());
		cardInformationRequest.setCardNumber(cardInformationDto.getCardNumber());
		cardInformationRequest.setExpirationDate(cardInformationDto.getExpirationDate());
		cardInformationRequest.setCvv(cardInformationDto.getCvv());
		cardInformationRequest.setUserId(UserId);

		return new SuccessResult(this.cardInformationService.add(cardInformationRequest).getMessage());
	}

}