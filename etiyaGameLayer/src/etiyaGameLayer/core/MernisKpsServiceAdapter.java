package etiyaGameLayer.core;

import etiyaGameLayer.entities.concretes.User;
import etiyaGameLayer.mernis.MernisKpsService;

public class MernisKpsServiceAdapter implements UserIdentityValidatorService{
	@Override
	public boolean isValid(User user) {
		MernisKpsService mernisKpsService = new MernisKpsService();
		return mernisKpsService.tcKimlikDogrula(Long.parseLong(user.getNationalIdentityNumber()), user.getFirstName(), user.getLastName(), user.getYearOfBirth());
	}
}
