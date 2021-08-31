package etiyaGameLayer.business.concretes;

import java.util.List;

import etiyaGameLayer.business.abstracts.GamerCheckService;
import etiyaGameLayer.business.abstracts.GamerService;
import etiyaGameLayer.core.UserIdentityValidatorService;
import etiyaGameLayer.dataAccess.abstracts.GamerDao;
import etiyaGameLayer.entities.concretes.Gamer;

public class GamerManager implements GamerService{
	
	UserIdentityValidatorService userIdentityValidatorService;
	GamerCheckService gamerCheckService;
	GamerDao gamerDao;

	public GamerManager(UserIdentityValidatorService userIdentityValidatorService, GamerCheckService gamerCheckService,
			GamerDao gamerDao) {
		super();
		this.userIdentityValidatorService = userIdentityValidatorService;
		this.gamerCheckService = gamerCheckService;
		this.gamerDao = gamerDao;
	}

	@Override
	public void add(Gamer gamer) {
		if(userIdentityValidatorService.isValid(gamer) && gamerCheckService.approval(gamer) ==true) {
			this.gamerDao.add(gamer);
		}
		
	}

	@Override
	public void update(Gamer gamer) {
		this.gamerDao.update(gamer);
		
	}

	@Override
	public void remove(Gamer gamer) {
		this.gamerDao.delete(gamer);
		
	}

	@Override
	public List<Gamer> getAll() {
		// TODO Auto-generated method stub
		return this.gamerDao.getAll();
	}

}
