package com.etiya.ReCapProject.business.abstracts;

import java.util.List;

import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CardInformation;
import com.etiya.ReCapProject.entities.requests.create.CreateCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCardInformationRequest;

public interface CardInformationService {
	
	DataResult<List<CardInformation>> getAll();

	DataResult<CardInformation> getById(int cardInformationId); 
	
	DataResult<List<CardInformation>> getCardInformationByApplicationUser_UserId(int applicationUserId);

	Result add(CreateCardInformationRequest createCardInformationRequest);

	Result update(UpdateCardInformationRequest updateCardInformationRequest);

	Result delete(DeleteCardInformationRequest deleteCardInformationRequest);
}
