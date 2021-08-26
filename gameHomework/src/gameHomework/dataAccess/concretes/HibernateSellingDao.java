package gameHomework.dataAccess.concretes;

import java.util.ArrayList;

import gameHomework.dataAccess.abstracts.SellingDao;
import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public class HibernateSellingDao implements SellingDao {

	ArrayList<Selling> selling = new ArrayList<Selling>();
	 
	@Override
	public void add(Selling selling) {	
		System.out.println("HibernateSellingDao ile eklendi : "+selling.getGame().getGameName() + " oyununu, " + selling.getGamer().getFirstName() + " oyuncusu "
				+ selling.getCampaing().getCampaignName() + " kampanyasi ile "+ selling.getGame().getGamePrice() +" TL yerine, "
				+ selling.getCampaignPrice() +" TL ye satin alindi. " );
		this.selling.add(selling);
	}

	@Override
	public ArrayList<Selling> list() {
		System.out.println();
		System.out.println("Hibernate ile listelendi : ");
		return this.selling;
		
	}

	

}
