package com.etiya.ReCapProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
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
public class CardInformationManager implements CardInformationService{
	
	private CardInformationDao cardInformationDao;
	private UserService userService;
	
	@Autowired
	public CardInformationManager(CardInformationDao cardInformationDao,UserService userService) {
		super();
		this.cardInformationDao = cardInformationDao;
		this.userService = userService;
	}

	@Override
	public DataResult<List<CardInformation>> getAll() {

		return new SuccessDataResult<List<CardInformation>>(this.cardInformationDao.findAll(), "Kart Bilgileri Listelendi.");
	}

	@Override
	public DataResult<CardInformation> getById(int cardInformationId) {

		return new SuccessDataResult<CardInformation>(this.cardInformationDao.getById(cardInformationId), "Kart Bilgisi Listelendi.");
	}

	@Override
	public DataResult<List<CardInformation>> getCardInformationByApplicationUser_UserId(int applicationUserId) {

		return new SuccessDataResult<List<CardInformation>>(this.cardInformationDao.getCardInformationByApplicationUser_UserId(applicationUserId) , "User Id e gore Listelendi");
	}

	@Override
	public Result add(CreateCardInformationRequest createCardInformationRequest) {
		
		ApplicationUser applicationUser = this.userService.getById(createCardInformationRequest.getUserId()).getData();

		CardInformation cardInformation = new CardInformation();
		cardInformation.setCardName(createCardInformationRequest.getCardName());
		cardInformation.setCardNumber(createCardInformationRequest.getCardNumber());
		cardInformation.setCvv(createCardInformationRequest.getCvv());
		cardInformation.setExpirationDate(createCardInformationRequest.getExpirationDate());

		cardInformation.setApplicationUser(applicationUser);
		
		this.cardInformationDao.save(cardInformation);
		return new SuccessResult("Kart Basariyla Eklendi.");
	}

	@Override
	public Result update(UpdateCardInformationRequest updateCardInformationRequest) {
		
		
		CardInformation cardInformation = this.cardInformationDao.getById(updateCardInformationRequest.getCardInformationId());
		cardInformation.setCardName(updateCardInformationRequest.getCardName());
		cardInformation.setCardNumber(updateCardInformationRequest.getCardNumber());
		cardInformation.setCvv(updateCardInformationRequest.getCvv());
		cardInformation.setExpirationDate(updateCardInformationRequest.getExpirationDate());

		
		this.cardInformationDao.save(cardInformation);
		return new SuccessResult("Kart Basariyla Guncelledi.");
	}

	@Override
	public Result delete(DeleteCardInformationRequest deleteCardInformationRequest) {
		
		
		CardInformation cardInformation = this.cardInformationDao.getById(deleteCardInformationRequest.getCardInformationId());

		this.cardInformationDao.delete(cardInformation);
		return new SuccessResult("Kart Basariyla Silindi.");
	}
	
	
}
