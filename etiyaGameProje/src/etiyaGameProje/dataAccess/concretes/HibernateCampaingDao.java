package etiyaGameProje.dataAccess.concretes;

import etiyaGameProje.dataAccess.abstracts.CampaingDao;
import etiyaGameProje.entities.Campaing;

public class HibernateCampaingDao implements CampaingDao{

	@Override
	public void add(Campaing campaing) {
		System.out.println( campaing.getCampaignName() +", hibarnateCampaingDao ile kampanya eklendi");
		
	}

	@Override
	public void list() {
		System.out.println( " hibarnate ile kampanyalar listelendi");
		
	}

	@Override
	public void update() {
		System.out.println( " hibarnate ile kampanya guncellendi");
		
	}

	@Override
	public void remove() {
		System.out.println( " hibarnate ile kampanya silindi");
		
	}

}
