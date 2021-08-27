package etiyaGameProje.businness.abstracts;

import etiyaGameProje.entities.User;

public interface UserCheckService {
	
	boolean checkFirstName(User customer);
	boolean checkLastName(User customer);
	boolean checkNationalIdentityNumber(User customer);
	boolean approval(User customer);
	
}
