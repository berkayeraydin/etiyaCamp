package com.etiya.ReCapProject.core.adapters;

import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.entities.requests.PosServiceRequest;
import com.etiya.ReCapProject.outServices.FakePosService;

@Service
public class FakePosServiceAdapter implements PaymentService{
	
	FakePosService fakePosService = new FakePosService();
	
	@Override
	public boolean withdraw(PosServiceRequest posServiceRequest) {
		
		return this.fakePosService.fakePos(posServiceRequest.getCardNumber(), posServiceRequest.getCardHolderName()
				, posServiceRequest.getExpirationDate(), posServiceRequest.getCvv(), posServiceRequest.getPrice());
	}
}
