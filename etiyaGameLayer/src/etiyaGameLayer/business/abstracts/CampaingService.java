package etiyaGameLayer.business.abstracts;

import java.util.List;

import etiyaGameLayer.entities.concretes.Campaing;

public interface CampaingService {
	
	void add(Campaing campaing);
	void remove(Campaing campaing);
	void update(Campaing campaing);
	List<Campaing> getAll();
}
