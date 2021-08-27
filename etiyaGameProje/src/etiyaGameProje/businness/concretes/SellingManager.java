package etiyaGameProje.businness.concretes;

import java.util.ArrayList;

import etiyaGameProje.businness.abstracts.SellingService;
import etiyaGameProje.dataAccess.concretes.HibernateSellingDao;
import etiyaGameProje.entities.Selling;

public class SellingManager implements SellingService {
	
	HibernateSellingDao hibernateSellingDao;

	public SellingManager( HibernateSellingDao hibernateSellingDao) {
		this.hibernateSellingDao = hibernateSellingDao;
	}
	

	@Override
	public void campaingSales(Selling selling) {
		
		this.hibernateSellingDao.campaingSellingAdd(selling);
		
	}
	
	@Override
	public void sales(Selling selling) {
		this.hibernateSellingDao.sellingAdd(selling);
		
	}

	@Override
	public ArrayList<Selling> sellingList() {
		return this.hibernateSellingDao.list();
	}


}
