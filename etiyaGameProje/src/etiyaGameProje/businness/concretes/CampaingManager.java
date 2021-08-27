package etiyaGameProje.businness.concretes;

import etiyaGameProje.businness.abstracts.CampaingService;
import etiyaGameProje.dataAccess.abstracts.CampaingDao;
import etiyaGameProje.entities.Campaing;


public class CampaingManager implements CampaingService {
	
	CampaingDao campaingDao;
	
	public CampaingManager(CampaingDao campaingDao) {
		this.campaingDao = campaingDao;
	
	}

	@Override
	public void add(Campaing campaing) {
		this.campaingDao.add(campaing);;
		
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
