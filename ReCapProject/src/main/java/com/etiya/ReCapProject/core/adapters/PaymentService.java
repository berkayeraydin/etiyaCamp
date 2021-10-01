package com.etiya.ReCapProject.core.adapters;

import com.etiya.ReCapProject.entities.requests.PosServiceRequest;

public interface PaymentService {
	
	public boolean withdraw(PosServiceRequest fakePosServiceRequest);
}
