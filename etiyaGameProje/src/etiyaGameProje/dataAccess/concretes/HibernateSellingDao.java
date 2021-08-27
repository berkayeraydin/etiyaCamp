package etiyaGameProje.dataAccess.concretes;

import java.util.ArrayList;

import etiyaGameProje.dataAccess.abstracts.SellingDao;
import etiyaGameProje.entities.Selling;


public class HibernateSellingDao implements SellingDao {
	
	ArrayList<Selling> sellings = new ArrayList<Selling>();
	
	@Override
	public void campaingSellingAdd(Selling selling) {	
		System.out.println("HibernateSellingDao ile eklendi : "+ selling.getGame().getGameName() + " oyununu, " + selling.getGamer().getFirstName() + " oyuncusu "
			+ selling.getCampaing().getCampaignName() + " kampanyasi ile "+ selling.getGame().getGamePrice()+" TL yerine, "
			+ selling.getCampaignPrice() +" TL ye satin alindi. " );
				
			this.sellings.add(selling);
	}
	
	@Override
	public void sellingAdd(Selling selling) {
		
		System.out.println("HibernateSellingDao ile eklendi : "+ selling.getGame().getGameName()+ " oyununu, " + selling.getGamer().getFirstName()+ " oyuncusu "
			+ selling.getGame().getGamePrice() +" TL ye satin aldi ");
	
		this.sellings.add(selling);
	}

	@Override
	public ArrayList<Selling> list() {
		System.out.println();
		System.out.println("Hibernate ile listelendi : ");
		return this.sellings;
		
	}

}
