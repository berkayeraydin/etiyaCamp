package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CardInformationService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.dtos.CardInformationDto;
import com.etiya.ReCapProject.entities.requests.create.CreateCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCardInformationRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCardInformationRequest;

@RestController
@RequestMapping("api/cardsinformation")
public class CardsInformationController {

	private CardInformationService cardInformationService;

	@Autowired
	public CardsInformationController(CardInformationService cardInformationService) {
		super();
		this.cardInformationService = cardInformationService;
	}

//	@GetMapping("/getAll")
//	public DataResult<List<CardInformation>> getAll() {
//		return this.cardInformationService.getAll();
//	}
//
//	@GetMapping("/getbyid")
//	public DataResult<CardInformation> getById(@RequestParam("carInformationId") int carInformationId) {
//		return this.cardInformationService.getById(carInformationId);
//	}

	@GetMapping("/getCardsInformationByUserId")
	DataResult<List<CardInformationDto>> getCardsInformationByApplicationUser_UserId(int applicationUserId) {
		return this.cardInformationService.getCardsInformationByApplicationUser_UserId(applicationUserId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCardInformationRequest createCardInformationRequest) {
		return this.cardInformationService.add(createCardInformationRequest);
	}

	@PutMapping("/update")
	public Result update(@Valid @RequestBody UpdateCardInformationRequest updateCardInformationRequest) {
		return this.cardInformationService.update(updateCardInformationRequest);
	}

	@DeleteMapping("/delete")
	public Result delte(@Valid DeleteCardInformationRequest deleteCardInformationRequest) {
		return this.cardInformationService.delete(deleteCardInformationRequest);
	}

}