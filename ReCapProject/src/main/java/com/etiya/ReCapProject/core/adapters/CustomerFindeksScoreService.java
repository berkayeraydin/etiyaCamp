package com.etiya.ReCapProject.core.adapters;

public interface CustomerFindeksScoreService {
	
	public int getCorporateScore(String taxNumber) ;
	
	public int getIndivicualScore(String nationalIdentityNumber) ;

}
