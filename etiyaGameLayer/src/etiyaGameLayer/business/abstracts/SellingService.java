package etiyaGameLayer.business.abstracts;

import java.util.List;

import etiyaGameLayer.entities.concretes.Selling;

public interface SellingService {
	
	void campaingSales(Selling selling);
	void sales(Selling selling);
	List<Selling> getAll();
}	
