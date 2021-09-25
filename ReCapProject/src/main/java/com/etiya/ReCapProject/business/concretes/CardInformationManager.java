package com.etiya.ReCapProject.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.constants.Messages;
import com.etiya.ReCapProject.core.utilities.businnes.BusinessRules;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.ErrorResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.core.utilities.result.SuccessDataResult;
import com.etiya.ReCapProject.core.utilities.result.SuccessResult;
import com.etiya.ReCapProject.dataAccess.abstracts.CardInformationDao;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.concretes.CardInformation;
import com.etiya.ReCapProject.entities.requests.create.CreateCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCardInformationRequest;

@Service
public class CardInformationManager implements CardInformationService {

	private CardInformationDao cardInformationDao;
	private UserService userService;

	@Autowired
	public CardInformationManager(CardInformationDao cardInformationDao, UserService userService) {
		super();
		this.cardInformationDao = cardInformationDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<CardInformation>> getAll() {
		return new SuccessDataResult<List<CardInformation>>(this.cardInformationDao.findAll(),
				Messages.CardInformationsListed);
	}

	@Override
	public DataResult<CardInformation> getById(int cardInformationId) {
		return new SuccessDataResult<CardInformation>(this.cardInformationDao.getById(cardInformationId),
				Messages.CardInformationListed);
	}

	@Override
	public DataResult<List<CardInformation>> getCardInformationsByApplicationUser_UserId(int applicationUserId) {
		return new SuccessDataResult<List<CardInformation>>(
				this.cardInformationDao.getCardInformationByApplicationUser_UserId(applicationUserId),
				Messages.CardInformationListedByUser);
	}

	@Override
	public Result add(CreateCardInformationRequest createCardInformationRequest) {
		
		var result = BusinessRules.run(this.checkCardFormat(createCardInformationRequest.getCardNumber()));

		if (result != null) {
			return result;
		}
		
		ApplicationUser applicationUser = this.userService.getById(createCardInformationRequest.getUserId()).getData();

		CardInformation cardInformation = new CardInformation();
		cardInformation.setCardName(createCardInformationRequest.getCardName());
		cardInformation.setCardNumber(createCardInformationRequest.getCardNumber());
		cardInformation.setCvv(createCardInformationRequest.getCvv());
		cardInformation.setExpirationDate(createCardInformationRequest.getExpirationDate());

		cardInformation.setApplicationUser(applicationUser);

		this.cardInformationDao.save(cardInformation);
		return new SuccessResult(Messages.CardInformationAdded);
	}

	@Override
	public Result update(UpdateCardInformationRequest updateCardInformationRequest) {
		
		var result = BusinessRules.run(this.checkCardFormat(updateCardInformationRequest.getCardNumber()));

		if (result != null) {
			return result;
		}

		CardInformation cardInformation = this.cardInformationDao
				.getById(updateCardInformationRequest.getCardInformationId());
		cardInformation.setCardName(updateCardInformationRequest.getCardName());
		cardInformation.setCardNumber(updateCardInformationRequest.getCardNumber());
		cardInformation.setCvv(updateCardInformationRequest.getCvv());
		cardInformation.setExpirationDate(updateCardInformationRequest.getExpirationDate());

		this.cardInformationDao.save(cardInformation);
		return new SuccessResult("Kart başariyla güncellendi.");
	}

	@Override
	public Result delete(DeleteCardInformationRequest deleteCardInformationRequest) {
		CardInformation cardInformation = this.cardInformationDao
				.getById(deleteCardInformationRequest.getCardInformationId());

		this.cardInformationDao.delete(cardInformation);
		return new SuccessResult(Messages.CardInformationUpdated);
	}

	@Override
	public Result checkCardFormat(String cardNumber) {

		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
				+ "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13})|"
				+ "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" + "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cardNumber);

		if (!matcher.find()) {
			return new ErrorResult(Messages.CardNumberTypeIsNotValid);
		}
		
		return new SuccessResult();
		
	}

	
	
}
