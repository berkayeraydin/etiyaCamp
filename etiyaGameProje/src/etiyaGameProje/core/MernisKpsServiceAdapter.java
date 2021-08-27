package etiyaGameProje.core;

import etiyaGameProje.entities.User;
import etiyaGameProje.mernis.MernisKpsService;


public class MernisKpsServiceAdapter implements UserIdentityValidatorService {

	@Override
	public boolean isValid(User user) {
		MernisKpsService mernisKpsService = new MernisKpsService();
		return mernisKpsService.tcKimlikDogrula(Long.parseLong(user.getNationalIdentityNumber()), user.getFirstName(), user.getLastName(), user.getYearOfBirth());
	}

}
