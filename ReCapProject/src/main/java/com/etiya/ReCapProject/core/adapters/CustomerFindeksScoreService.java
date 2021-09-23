package com.etiya.ReCapProject.core.adapters;

public interface CustomerFindeksScoreService {
	
	public int getCorporateFindeksScore(String taxNumber) ;
	
	public int getIndividualFindeksScore(String nationalIdentityNumber) ;

}
