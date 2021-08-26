package gameHomework.dataAccess.abstracts;

import gameHomework.entities.concretes.Campaing;

public interface CampaingDao {
	
	void add(Campaing Campaing);
	void list();
	void update();
	void remove();
	
}
