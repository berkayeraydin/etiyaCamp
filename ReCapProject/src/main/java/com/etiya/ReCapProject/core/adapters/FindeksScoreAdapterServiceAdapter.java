package com.etiya.ReCapProject.core.adapters;

import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.outServices.FindeksScoreService;

@Service
public class FindeksScoreAdapterServiceAdapter implements CustomerFindeksScoreService {
	
	FindeksScoreService findeksScoreService = new FindeksScoreService();
	
	@Override
	public int getCorporateScore(String taxNumber) {
		
		
		return findeksScoreService.getCorporateScore(taxNumber);
	}

	@Override
	public int getIndivicualScore(String nationalIdentityNumber) {

		return findeksScoreService.getIndivicualScore(nationalIdentityNumber);
	}

}
