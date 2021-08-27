package etiyaGameProje.businness.abstracts;

import java.util.ArrayList;

import etiyaGameProje.entities.Selling;


public interface SellingService {
	
	void campaingSales(Selling selling);
	void sales(Selling selling);
	ArrayList<Selling> sellingList();
}
