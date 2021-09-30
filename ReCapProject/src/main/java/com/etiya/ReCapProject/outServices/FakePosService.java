package com.etiya.ReCapProject.outServices;

public class FakePosService {
	
	public boolean fakePos(String cardNumber,String cardHolderName,String expirationDate,String cvv, double price) {
		
		double limit = 3500;
		
		if (price < limit) {
			return true;
		}
		return false;
	}
	
}
