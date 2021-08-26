package gameHomework.business.concretes;

import gameHomework.business.abstracts.VerificationService;
import gameHomework.entities.concretes.Gamer;


public class VerificationManager implements VerificationService {

	@Override
	public void verification(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " kisisi E devlet tarafindan dogrulandi");
		
	}

}
