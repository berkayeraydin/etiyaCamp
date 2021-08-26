package gameHomework.business.concretes;

import gameHomework.business.abstracts.GamerService;
import gameHomework.business.abstracts.VerificationService;
import gameHomework.dataAccess.abstracts.GamerDao;
import gameHomework.entities.concretes.Gamer;

public class GamerManager implements GamerService {
	
	VerificationService verificationService;
	GamerDao gamerDao;
	
	public GamerManager(VerificationService verificationService, GamerDao gamerDao){
		this.verificationService = verificationService;
		this.gamerDao=gamerDao;
	}

	@Override
	public void register(Gamer gamer) {
		this.verificationService.verification(gamer);
		System.out.println(gamer.getFirstName() +"  Basariyla kayit oldunuz. " );
		this.gamerDao.add();
		// gamer
	}

	
	@Override
	public void update(Gamer gamer) {
		this.gamerDao.update();
		
	}

	@Override
	public void remove(Gamer gamer) {
		this.gamerDao.remove();
		
	}

}
