package etiyaGameLayer.dataAccess.concretes.jdbc;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.CampaingDao;
import etiyaGameLayer.entities.concretes.Campaing;

public class JdbcCampaingDao implements CampaingDao {
	
	List<Campaing> campaings = new ArrayList<Campaing>();

	@Override
	public List<Campaing> getAll() {
		// TODO Auto-generated method stub
		return this.campaings;
	}

	@Override
	public void add(Campaing entity) {
		System.out.println(entity.getCampaignName() + " JdbcCampaingDao ile Eklendi");
		this.campaings.add(entity);
		
	}

	@Override
	public void update(Campaing entity) {
		System.out.println(entity.getCampaignName() + " JdbcCampaingDao ile Guncellendi");
		
	}

	@Override
	public void delete(Campaing entity) {
		System.out.println(entity.getCampaignName() + " JdbcCampaingDao ile Silindi");
		
	}
}
