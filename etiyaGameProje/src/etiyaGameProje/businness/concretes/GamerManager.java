package etiyaGameProje.businness.concretes;

import java.util.ArrayList;

import etiyaGameProje.businness.abstracts.BaseCalculateGameService;
import etiyaGameProje.businness.abstracts.GamerService;
import etiyaGameProje.businness.abstracts.UserCheckService;
import etiyaGameProje.core.UserIdentityValidatorService;
import etiyaGameProje.dataAccess.abstracts.GamerDao;
import etiyaGameProje.entities.Gamer;


public class GamerManager implements GamerService {
	
	UserIdentityValidatorService userIdentityValidatorService;
	UserCheckService userCheckService;
	GamerDao gamerDao;
	
	
	public GamerManager(UserIdentityValidatorService userIdentityValidatorService,UserCheckService userCheckService,GamerDao gamerDao) {
		super();
		this.userIdentityValidatorService = userIdentityValidatorService;
		this.userCheckService=userCheckService;
		this.gamerDao=gamerDao;
	}

	@Override
	public void add(Gamer gamer) {
		if(userIdentityValidatorService.isValid(gamer) && userCheckService.approval(gamer) ==true) {
			System.out.println(gamer.getFirstName()+ " Eklendi");
			this.gamerDao.add(gamer);
		}
		
	}

	@Override
	public void update(Gamer gamer) {
		this.gamerDao.update(gamer);
		
	}

	@Override
	public void remove(Gamer gamer) {
		this.gamerDao.remove(gamer);
		
	}

	@Override
	public ArrayList<Gamer> gamerList() {
		return this.gamerDao.list();
	}

}
