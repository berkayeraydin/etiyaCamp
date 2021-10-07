package com.etiya.ReCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.business.abstracts.CityService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.InvoiceService;
import com.etiya.ReCapProject.business.abstracts.ModelMapperService;
import com.etiya.ReCapProject.business.abstracts.RentalAdditionalService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.adapters.CustomerFindeksScoreService;
import com.etiya.ReCapProject.core.adapters.PaymentService;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
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
import com.etiya.ReCapProject.entities.concretes.RentalAdditional;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.dtos.CardInformationDto;
import com.etiya.ReCapProject.entities.dtos.RentalAdditionalDetailDto;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.CarReturnedRequest;
import com.etiya.ReCapProject.entities.requests.PosServiceRequest;
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
	private PaymentService paymentService;
	private RentalAdditionalService rentalAdditionalService;
	private ModelMapperService modelMapperService;

	@Autowired
	public RentalManager(RentalDao rentalDao, CarService carService, UserService userService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			CustomerFindeksScoreService customerFindeksScoreService, CardInformationService cardInformationService,
			InvoiceService invoiceService, CityService cityService, PaymentService paymentService,
			RentalAdditionalService rentalAdditionalService, ModelMapperService modelMapperService) {
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
		this.paymentService = paymentService;
		this.rentalAdditionalService = rentalAdditionalService;
		this.modelMapperService = modelMapperService;
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

		RentalDetailDto rentalDetailDto = modelMapperService.forDto().map(rental, RentalDetailDto.class);

		CarDetailDto carDetailDto = this.carService.getCarDetailsByCarId(rental.getCar().getCarId()).getData();
		rentalDetailDto.setCarDetailDto(carDetailDto);

		List<RentalAdditionalDetailDto> rentalAdditionalDetailDtos = new ArrayList<RentalAdditionalDetailDto>();

		for (RentalAdditional rentalAdditional : rental.getRentalAdditionals()) {

			RentalAdditionalDetailDto rentalAdditionalDetailDto = new RentalAdditionalDetailDto();
			rentalAdditionalDetailDto.setRentalAdditionalName(rentalAdditional.getRentalAdditionalName());
			rentalAdditionalDetailDto.setDailyPrice(rentalAdditional.getDailyPrice());

			rentalAdditionalDetailDtos.add(rentalAdditionalDetailDto);
		}

		rentalDetailDto.setRentalAdditionalDetailDtos(rentalAdditionalDetailDtos);

		return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, Messages.RentalDetail);

	}

	@Override
	public DataResult<List<RentalDetailDto>> getRentalsDetailByApplicationUserId(int applicationUserId) {

		List<Rental> rentals = this.rentalDao.getByApplicationUser_UserId(applicationUserId);

		List<RentalDetailDto> rentalDetailDtos = new ArrayList<RentalDetailDto>();

		for (Rental rental : rentals) {

			RentalDetailDto rentalDetailDto = modelMapperService.forDto().map(rental, RentalDetailDto.class);
			CarDetailDto carDetailDto = this.carService.getCarDetailsByCarId(rental.getCar().getCarId()).getData();

			rentalDetailDto.setCarDetailDto(carDetailDto);

			List<RentalAdditionalDetailDto> rentalAdditionalDetailDtos = new ArrayList<RentalAdditionalDetailDto>();

			for (RentalAdditional rentalAdditional : rental.getRentalAdditionals()) {

				RentalAdditionalDetailDto rentalAdditionalDetailDto = modelMapperService.forDto().map(rentalAdditional,
						RentalAdditionalDetailDto.class);

				rentalAdditionalDetailDtos.add(rentalAdditionalDetailDto);
			}

			rentalDetailDto.setRentalAdditionalDetailDtos(rentalAdditionalDetailDtos);

			rentalDetailDtos.add(rentalDetailDto);
		}

		return new SuccessDataResult<List<RentalDetailDto>>(rentalDetailDtos, Messages.RentalDetails);
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		var result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()),
				checkCustomerFindeksScore(createRentalRequest.getUserId(), createRentalRequest.getCarId()),
				this.cardInformationService
						.checkCardFormat(createRentalRequest.getCardInformationDto().getCardNumber()),
				this.carService.checkCarIsInGallery(createRentalRequest.getCarId()),
				this.checkPaymentService(createRentalRequest.getCardInformationDto(),
						this.getRentalTotalPrice(createRentalRequest).getData()));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();
		this.carService.carListedIsFalse(car.getCarId());

		ApplicationUser applicationUser = this.userService.getById(createRentalRequest.getUserId()).getData();

		City takeCity = car.getCity();

		City returnCity = this.cityService.getById(createRentalRequest.getReturnCityId()).getData();

		Rental rental =  modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		rental.setCar(car);
		rental.setRentKilometer(car.getKilometer());
		rental.setApplicationUser(applicationUser);
		rental.setTakeCity(takeCity);
		rental.setReturnCity(returnCity);
		

		List<RentalAdditional> rentalAdditionals = getRentalAdditionals(createRentalRequest.getRentalAdditionalsId()).getData();
			

		if (takeCity.getCityId() != returnCity.getCityId()) {
			RentalAdditional rentalAdditional1 = this.rentalAdditionalService.getById(1).getData();
			rentalAdditionals.add(rentalAdditional1);
		}

		rental.setRentalAdditionals(rentalAdditionals);

		this.rentalDao.save(rental);

		if (createRentalRequest.isShouldCardBeSaved()) {
			this.cardInformationSavedIfCardIsSavedIsTrue(createRentalRequest.getCardInformationDto(),
					createRentalRequest.getUserId());
		}

		return new SuccessResult(Messages.RentalAdded);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		var result = BusinessRules.run();

		if (result != null) {
			return result;
		}
		
		Rental rental = this.getUpdatedRental(updateRentalRequest).getData();

		this.rentalDao.save(rental);

		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {

		Rental rental = this.rentalDao.getById(deleteRentalRequest.getRentalId());

		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RentalDeleted);
	}

	// Kiralama işlemi arabanın müşteriden teslim alınmasıyla bitirilir
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

		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		createInvoiceRequest.setRental(rental);

		this.invoiceService.add(createInvoiceRequest);

		return new SuccessResult(Messages.RentalCarIsReturned);
	}

	// Hizmetin toplam turarını hesaplar
	@Override
	public DataResult<Double> getRentalAdditionalTotalPrice(int rentalAdditionalId, Date minDate, Date maxDate) {

		long totalRentalDay = ChronoUnit.DAYS.between(minDate.toInstant(), maxDate.toInstant());

		RentalAdditional rentalAdditional = this.rentalAdditionalService.getById(rentalAdditionalId).getData();
		double rentalAdditionalTotalPrice = rentalAdditional.getDailyPrice() * totalRentalDay;

		return new SuccessDataResult<Double>(rentalAdditionalTotalPrice);
	}

	// Arabanın galeride olup olmadığını kontrol eder
	private Result checkCarIsReturned(int carId) {

		if (this.rentalDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.RentalCarNotReturn);
		}
		return new SuccessResult();
	}

	// Arabanın teslim edildiği şehir farklıysa arabanın şehir bilgisi günceller
	private DataResult<Car> carCityUpdateIfReturnedDifferentCity(int carId, int cityId) {

		Car car = this.carService.getById(carId).getData();

		if (car.getCity().getCityId() != cityId) {

			City city = this.cityService.getById(cityId).getData();
			car.setCity(city);
		}

		return new SuccessDataResult<Car>(car);
	}

	// Müşterinin findeks puanın yeterli olup olmadığını kontrol eder
	private Result checkCustomerFindeksScore(int applicationUserId, int carId) {

		if (this.individualCustomerService.existsByUserId(applicationUserId).isSuccess()) {
			IndividualCustomer individualCustomer = this.individualCustomerService
					.getByApplicationUser_UserId(applicationUserId).getData();

			if (this.carService.getById(carId).getData().getMinFindeksScore() > this.customerFindeksScoreService
					.getIndivicualScore(individualCustomer.getNationalIdentityNumber())) {

				return new ErrorResult(Messages.FindeksScoreIsInsufficient);
			}

		}

		if (this.corporateCustomerService.existsByUserId(applicationUserId).isSuccess()) {

			CorporateCustomer corporateCustomer = this.corporateCustomerService
					.getByApplicationUser_UserId(applicationUserId).getData();

			if (this.carService.getById(carId).getData().getMinFindeksScore() > this.customerFindeksScoreService
					.getCorporateScore(corporateCustomer.getTaxNumber())) {

				return new ErrorResult(Messages.FindeksScoreIsInsufficient);
			}
		}

		return new SuccessResult();
	}

	// Müşteri kiralama sırasında kart bilgilerini kaydetmek isterse kart
	// bilgilerini kayıt eder
	private Result cardInformationSavedIfCardIsSavedIsTrue(CardInformationDto cardInformationDto, int UserId) {

		CreateCardInformationRequest createCardInformationRequest = new CreateCardInformationRequest();
		createCardInformationRequest.setCardHolderName(cardInformationDto.getCardHolderName());
		createCardInformationRequest.setCardName(cardInformationDto.getCardName());
		createCardInformationRequest.setCardNumber(cardInformationDto.getCardNumber());
		createCardInformationRequest.setExpirationDate(cardInformationDto.getExpirationDate());
		createCardInformationRequest.setCvv(cardInformationDto.getCvv());
		createCardInformationRequest.setUserId(UserId);

		return new SuccessResult(this.cardInformationService.add(createCardInformationRequest).getMessage());
	}

	// Kiralama sırasında kart limitini kontrol eder
	private Result checkPaymentService(CardInformationDto cardInformationDto, double price) {

		PosServiceRequest fakePosServiceRequest = new PosServiceRequest();
		fakePosServiceRequest.setCardNumber(cardInformationDto.getCardName());
		fakePosServiceRequest.setCardHolderName(cardInformationDto.getCardHolderName());
		fakePosServiceRequest.setExpirationDate(cardInformationDto.getExpirationDate());
		fakePosServiceRequest.setCvv(cardInformationDto.getCvv());
		fakePosServiceRequest.setPrice(price);

		if (!this.paymentService.withdraw(fakePosServiceRequest)) {
			return new ErrorResult(Messages.PaymentError);
		}

		return new SuccessResult();
	}

	// Kiralama işlem alınan bütün hizmetlerin toplam tutarını hesaplar
	private DataResult<Double> getRentalTotalPrice(CreateRentalRequest createRentalRequest) {

		long totalRentalDay = ChronoUnit.DAYS.between(createRentalRequest.getRentDate().toInstant(),
				createRentalRequest.getReturnDate().toInstant());

		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();

		double totalPrice = car.getDailyPrice() * totalRentalDay;

		for (int rentalAdditionalId : createRentalRequest.getRentalAdditionalsId()) {
			totalPrice += this.getRentalAdditionalTotalPrice(rentalAdditionalId, createRentalRequest.getRentDate(),
					createRentalRequest.getReturnDate()).getData();
		}

		if (car.getCity().getCityId() != createRentalRequest.getReturnCityId()) {

			RentalAdditional rentalAdditional = this.rentalAdditionalService.getById(1).getData();
			totalPrice += rentalAdditional.getDailyPrice();
		}

		return new SuccessDataResult<Double>(totalPrice);
	}
	
	// id listesi verilen RentalAdditional lari liste seklinde doner.
	private DataResult<List<RentalAdditional>> getRentalAdditionals(List<Integer> rentalAdditionalsId){
		
		List<RentalAdditional> rentalAdditionals = new ArrayList<RentalAdditional>();

		for (int rentalAdditionalId : rentalAdditionalsId) {

			RentalAdditional rentalAdditional = new RentalAdditional();
			rentalAdditional.setRentalAdditionalId(rentalAdditionalId);

			rentalAdditionals.add(rentalAdditional);
		}
		return new SuccessDataResult<List<RentalAdditional>>(rentalAdditionals);
	}
	
	// Guncellenen returnCity bilgisine gore rental dondurur.
	private DataResult<Rental> getUpdatedRental(UpdateRentalRequest updateRentalRequest){
		
		City returnCity = this.cityService.getById(updateRentalRequest.getReturnCityId()).getData();

		Rental rental = this.rentalDao.getById(updateRentalRequest.getRentalId());

		List<RentalAdditional> rentalAdditionals = new ArrayList<RentalAdditional>();

		if (rental.getReturnCity().getCityId() != returnCity.getCityId()) {

			if (rental.getTakeCity().getCityId() == returnCity.getCityId()) {

				for (RentalAdditional rentalAdditional : rental.getRentalAdditionals()) {

					if (rentalAdditional.getRentalAdditionalId() != 1) {

						rentalAdditionals.add(rentalAdditional);
					}
				}
				rental.setRentalAdditionals(rentalAdditionals);
			}

			if (rental.getTakeCity().getCityId() == rental.getReturnCity().getCityId()) {

				if (rental.getTakeCity().getCityId() != returnCity.getCityId()) {

					for (RentalAdditional rentalAdditional : rental.getRentalAdditionals()) {

						rentalAdditionals.add(rentalAdditional);

					}
					RentalAdditional rentalAdditional = this.rentalAdditionalService.getById(1).getData();
					rentalAdditionals.add(rentalAdditional);

					rental.setRentalAdditionals(rentalAdditionals);
				}
			}
			rental.setReturnCity(returnCity);
		}
		return new SuccessDataResult<Rental>(rental);
	}
	

}