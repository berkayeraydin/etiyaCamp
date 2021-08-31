package etiyaGameLayer.business.concretes;

import java.util.List;

import etiyaGameLayer.business.abstracts.CampaingService;
import etiyaGameLayer.dataAccess.abstracts.CampaingDao;
import etiyaGameLayer.entities.concretes.Campaing;

public class CampaingManager implements CampaingService{
	
	CampaingDao campaingDao;

	public CampaingManager(CampaingDao campaingDao) {
		super();
		this.campaingDao = campaingDao;
	}

	@Override
	public void add(Campaing campaing) {
		this.campaingDao.add(campaing);
		
	}

	@Override
	public void remove(Campaing campaing) {
		this.campaingDao.delete(campaing);
		
	}

	@Override
	public void update(Campaing campaing) {
		this.campaingDao.update(campaing);
		
	}

	@Override
	public List<Campaing> getAll() {
		// TODO Auto-generated method stub
		return this.campaingDao.getAll();
	}

}
