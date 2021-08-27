package etiyaGameProje.dataAccess.abstracts;

import java.util.ArrayList;

import etiyaGameProje.entities.Selling;

public interface SellingDao {
	
	void campaingSellingAdd(Selling selling);
	
	void sellingAdd(Selling selling);
	
	ArrayList<Selling> list();
}
