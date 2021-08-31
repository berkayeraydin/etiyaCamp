package etiyaGameLayer.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.CampaingDao;
import etiyaGameLayer.entities.concretes.Campaing;

public class HibernateCampaingDao implements CampaingDao{
	
	List<Campaing> campaings = new ArrayList<Campaing>();

	@Override
	public List<Campaing> getAll() {
		// TODO Auto-generated method stub
		return this.campaings;
	}

	@Override
	public void add(Campaing entity) {
		System.out.println(entity.getCampaignName() + " HibernateCampaingDao ile Eklendi");
		this.campaings.add(entity);
		
	}

	@Override
	public void update(Campaing entity) {
		System.out.println(entity.getCampaignName() + " HibernateCampaingDao ile Guncellendi");
		
	}

	@Override
	public void delete(Campaing entity) {
		System.out.println(entity.getCampaignName() + " HibernateCampaingDao ile Silindi");
		
	}

}
