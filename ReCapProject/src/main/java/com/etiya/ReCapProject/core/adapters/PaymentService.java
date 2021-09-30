package com.etiya.ReCapProject.core.adapters;

import com.etiya.ReCapProject.entities.requests.FakePosServiceRequest;

public interface PaymentService {
	
	public boolean pos(FakePosServiceRequest fakePosServiceRequest);
}
