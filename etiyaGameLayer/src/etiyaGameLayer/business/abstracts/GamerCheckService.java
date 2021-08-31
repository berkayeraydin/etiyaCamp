package etiyaGameLayer.business.abstracts;

import etiyaGameLayer.entities.concretes.Gamer;

public interface GamerCheckService {
	
	boolean checkFirstName(Gamer customer);
	boolean checkLastName(Gamer customer);
	boolean checkNationalIdentityNumber(Gamer customer);
	boolean approval(Gamer customer);
}
