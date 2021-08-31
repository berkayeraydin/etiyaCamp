package etiyaGameLayer.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.SellingDao;
import etiyaGameLayer.entities.concretes.Selling;

public class HibernateSellingDao implements SellingDao {
	
	List<Selling> sellings = new ArrayList<Selling>();
	
	@Override
	public List<Selling> getAll() {
		System.out.println("HibernateSellingDao ile listelendi : ");
		return this.sellings;
	}

	@Override
	public void add(Selling entity) {
		System.out.println("HibernateSellingDao ile eklendi : "+ entity.getGame().getGameName()+ " oyununu, " + entity.getGamer().getFirstName()+ " oyuncusu "
				+ entity.getGame().getGamePrice() +" TL ye satin aldi ");
		
			this.sellings.add(entity);
		
	}

	@Override
	public void update(Selling entity) {
		System.out.println("HibernateSellingDao ile Guncellendi");
		
	}

	@Override
	public void delete(Selling entity) {
		System.out.println("HibernateSellingDao ile Silindi");
		
	}

	@Override
	public void campaingSellingAdd(Selling selling) {
		System.out.println("HibernateSellingDao ile eklendi : "+ selling.getGame().getGameName() + " oyununu, " + selling.getGamer().getFirstName() + " oyuncusu "
				+ selling.getCampaing().getCampaignName() + " kampanyasi ile "+ selling.getGame().getGamePrice()+" TL yerine, "
				+ selling.getCampaignPrice() +" TL ye satin alindi. " );
					
				this.sellings.add(selling);
		
	}
	
	
}
