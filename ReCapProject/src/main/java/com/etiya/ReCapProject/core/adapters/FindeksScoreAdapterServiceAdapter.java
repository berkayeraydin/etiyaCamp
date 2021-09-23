package com.etiya.ReCapProject.core.adapters;

import org.springframework.stereotype.Service;

import com.etiya.ReCapProject.outServices.FindeksScoreService;

@Service
public class FindeksScoreAdapterServiceAdapter implements CustomerFindeksScoreService {
	
	FindeksScoreService findeksScoreService = new FindeksScoreService();
	
	@Override
	public int getCorporateFindeksScore(String taxNumber) {
		
		
		return findeksScoreService.getCorporateFindeksScore(taxNumber);
	}

	@Override
	public int getIndividualFindeksScore(String nationalIdentityNumber) {

		return findeksScoreService.getCorporateFindeksScore(nationalIdentityNumber);
	}

}
