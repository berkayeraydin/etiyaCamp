package gameHomework.dataAccess.concretes;

import java.util.ArrayList;

import gameHomework.dataAccess.abstracts.SellingDao;
import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public class HibernateSellingDao implements SellingDao {

	ArrayList<Selling> sellings = new ArrayList<Selling>();
	
	@Override
	public void campaingSellingAdd(Game game, Gamer gamer) {	
			System.out.println("HibernateSellingDao ile eklendi : "+ game.getGameName() + " oyununu, " + gamer.getFirstName() + " oyuncusu "
					+ game.getCampaing().getCampaignName() + " kampanyasi ile "+ game.getGamePrice() +" TL yerine, "
						+ game.getCampaignPrice() +" TL ye satin alindi. " );
				
			Selling selling = new Selling(gamer,game);
			this.sellings.add(selling);
	}
	
	@Override
	public void sellingAdd(Game game, Gamer gamer) {
		System.out.println("HibernateSellingDao ile eklendi : "+ game.getGameName() + " oyununu, " + gamer.getFirstName() + " oyuncusu "
				+ game.getGamePrice() +" TL ye satin aldi ");
		Selling selling = new Selling(gamer,game);
		this.sellings.add(selling);
	}

	@Override
	public ArrayList<Selling> list() {
		System.out.println();
		System.out.println("Hibernate ile listelendi : ");
		return this.sellings;
		
	}

	

	

}
