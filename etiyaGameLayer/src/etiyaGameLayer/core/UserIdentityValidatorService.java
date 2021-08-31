package etiyaGameLayer.core;

import etiyaGameLayer.entities.concretes.User;

public interface UserIdentityValidatorService {
	
	boolean isValid(User user);
}
