package gameHomework.business.concretes;

import gameHomework.business.abstracts.CampaingService;
import gameHomework.dataAccess.abstracts.CampaingDao;
import gameHomework.entities.concretes.Campaing;

public class CampaingManager implements CampaingService{
	
	
	CampaingDao campaingDao;
	
	
	public CampaingManager(CampaingDao campaingDao) {
		this.campaingDao = campaingDao;
	
	}

	@Override
	public void add(Campaing campaing) {
		this.campaingDao.add(campaing);
		
	}

	@Override
	public void remove() {
		this.campaingDao.remove();
	}

	@Override
	public void update() {
		this.campaingDao.update();
		
	}

}
