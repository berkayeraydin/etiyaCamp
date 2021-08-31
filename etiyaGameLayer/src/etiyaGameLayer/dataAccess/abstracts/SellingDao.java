package etiyaGameLayer.dataAccess.abstracts;

import etiyaGameLayer.entities.concretes.Selling;

public interface SellingDao extends EntityRepository<Selling>{
	
	void campaingSellingAdd(Selling selling);
	
}
