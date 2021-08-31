package etiyaGameLayer.business.concretes;

import java.util.List;

import etiyaGameLayer.business.abstracts.SellingService;
import etiyaGameLayer.dataAccess.abstracts.SellingDao;
import etiyaGameLayer.entities.concretes.Selling;

public class SellingManager implements SellingService {
	
	SellingDao sellingDao;
	
	public SellingManager(SellingDao sellingDao) {
		super();
		this.sellingDao = sellingDao;
	}

	@Override
	public void campaingSales(Selling selling) {
		this.sellingDao.campaingSellingAdd(selling);
		
	}

	@Override
	public void sales(Selling selling) {
		this.sellingDao.add(selling);
		
	}

	@Override
	public List<Selling> sellingList() {
		// TODO Auto-generated method stub
		return this.sellingDao.getAll();
	}

	

}
