package gameHomework.dataAccess.concretes;

import gameHomework.dataAccess.abstracts.CampaingDao;
import gameHomework.entities.concretes.Campaing;

public class HibernateCampaingDao implements CampaingDao {

	@Override
	// Campaing campaing
	public void add(Campaing campaing) {
		System.out.println( " hibarnate ile kampanya eklendi");
		
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
