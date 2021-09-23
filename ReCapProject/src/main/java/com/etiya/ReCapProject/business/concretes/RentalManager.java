package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CarService;
import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.business.abstracts.IndividualCustomerService;
import com.etiya.ReCapProject.business.abstracts.RentalService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.adapters.CustomerFindeksScoreService;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.RentalDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.Car;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
import com.etiya.ReCapProject.entities.concretes.IndividualCustomer;
import com.etiya.ReCapProject.entities.concretes.Rental;
import com.etiya.ReCapProject.entities.dtos.CarDetailDto;
import com.etiya.ReCapProject.entities.dtos.CardInformationDto;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.IndividualCustomerDetailDto;
import com.etiya.ReCapProject.entities.dtos.RentalDetailDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCardInformationRequest;
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

	@Autowired
	public RentalManager(RentalDao rentalDao, CarService carService, UserService userService,
			IndividualCustomerService individualCustomerService,CorporateCustomerService corporateCustomerService,
			CustomerFindeksScoreService customerFindeksScoreService ,CardInformationService cardInformationService) {
		super();
		this.rentalDao = rentalDao;
		this.carService = carService;
		this.userService = userService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.customerFindeksScoreService = customerFindeksScoreService;
		this.cardInformationService = cardInformationService;
		
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
	public Result add(CreateRentalRequest createRentalRequest) {
		
		var result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId())
				,checkCustomerFindeksScore(createRentalRequest.getUserId(),createRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}

		Car car = this.carService.getById(createRentalRequest.getCarId()).getData();

		ApplicationUser applicationUser = this.userService.getById(createRentalRequest.getUserId()).getData();

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());

		rental.setCar(car);
		rental.setApplicationUser(applicationUser);

		this.rentalDao.save(rental);
		if(createRentalRequest.isCardIsSaved()) {
			carInformationSavedIfCardIsSavedIsTrue(createRentalRequest.getCardInformationDto() , createRentalRequest.getUserId());
		}
		return new SuccessResult(Messages.RentalAdded);
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		var result = BusinessRules.run(checkCarIsReturned(updateRentalRequest.getCarId())
				,checkCustomerFindeksScore(this.rentalDao.getById(updateRentalRequest.getRentalId()).getApplicationUser().getUserId(),updateRentalRequest.getCarId()));

		if (result != null) {
			return result;
		}
		

		Car car = this.carService.getById(updateRentalRequest.getCarId()).getData();

		Rental rental = this.rentalDao.getById(updateRentalRequest.getRentalId());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setRentalId(updateRentalRequest.getRentalId());

		rental.setCar(car);

		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RentalUpdated);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {

		Rental rental = this.rentalDao.getById(deleteRentalRequest.getRentalId());

		this.rentalDao.delete(rental);
		return new SuccessResult(Messages.RentalDeleted);
	}

	private Result checkCarIsReturned(int carId) {
		if (this.rentalDao.existsByIsCarReturnedIsFalseAndCar_CarId(carId)) {
			return new ErrorResult(Messages.RentalCarNotReturn);
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<RentalDetailDto> getRentalDetailsByRentalId(int rentalId) {

		Rental rental = this.rentalDao.getById(rentalId);

		RentalDetailDto rentalDetailDto = new RentalDetailDto();
		CarDetailDto carDetailDto = this.carService.getCarDetailsByCarId(rental.getCar().getCarId()).getData();

		if (this.individualCustomerService.existsByUserId(rental.getApplicationUser().getUserId()).isSuccess()) {
			
			IndividualCustomerDetailDto individualCustomerDetailDto = this.individualCustomerService
					.getIndividualCustomerDetailsById(rental.getApplicationUser().getIndividualCustomer().getIndividualCustomerId()).getData();
			
			rentalDetailDto.setRentDate(rental.getRentDate());
			rentalDetailDto.setReturnDate(rental.getReturnDate());
			rentalDetailDto.setCarDetailDto(carDetailDto);
			rentalDetailDto.setCustomerDto(individualCustomerDetailDto);

			return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, "kiralama işlemi detayları");
		}
		
		if (this.corporateCustomerService.existsByUserId(rental.getApplicationUser().getUserId()).isSuccess()) {
			
			CorporateCustomerDetailDto corporateCustomerDetailDto = this.corporateCustomerService
					.getCorporateCustomerDetailsById(rental.getApplicationUser().getCorporateCustomer().getCorporateCustomerId()).getData();
			
			rentalDetailDto.setRentDate(rental.getRentDate());
			rentalDetailDto.setReturnDate(rental.getReturnDate());
			rentalDetailDto.setCarDetailDto(carDetailDto);
			rentalDetailDto.setCustomerDto(corporateCustomerDetailDto);

			return new SuccessDataResult<RentalDetailDto>(rentalDetailDto, "kiralama işlemi detayları");
		}
		
		return null;
	}
	
	private Result checkCustomerFindeksScore(int applicationUserId, int carId) {
		
		if(this.individualCustomerService.existsByUserId(applicationUserId).isSuccess()) {
			IndividualCustomer individualCustomer = this.individualCustomerService.getByApplicationUser_UserId(applicationUserId).getData();
			
			if(this.carService.getById(carId).getData().getMinFindeksScore()  > 
			this.customerFindeksScoreService.getIndividualFindeksScore(individualCustomer.getNationalIdentityNumber())) {
				
				return new ErrorResult("Findeks Puani Yetersiz.");
			}
			
	
		}else if(this.corporateCustomerService.existsByUserId(applicationUserId).isSuccess()) {
			CorporateCustomer corporateCustomer = this.corporateCustomerService.getByApplicationUser_UserId(applicationUserId).getData();
			
			if(this.carService.getById(carId).getData().getMinFindeksScore() > 
			this.customerFindeksScoreService.getCorporateFindeksScore(corporateCustomer.getTaxNumber())) {
				
				return new ErrorResult("Findeks Puani Yetersiz.");
			}
			
		}
		
		return new SuccessResult();
	}
	
	
	private Result carInformationSavedIfCardIsSavedIsTrue(CardInformationDto cardInformationDto , int userId) {
		
		CreateCardInformationRequest cardInformationRequest = new CreateCardInformationRequest();
		cardInformationRequest.setCardName(cardInformationDto.getCardName());
		cardInformationRequest.setCardNumber(cardInformationDto.getCardNumber());
		cardInformationRequest.setExpirationDate(cardInformationDto.getExpirationDate());
		cardInformationRequest.setCvv(cardInformationDto.getCvv());
		
		cardInformationRequest.setUserId(userId);
		
		return new SuccessResult(this.cardInformationService.add(cardInformationRequest).getMessage());
		
	}
}