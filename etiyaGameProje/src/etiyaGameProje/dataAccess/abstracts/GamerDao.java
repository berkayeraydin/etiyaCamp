package etiyaGameProje.dataAccess.abstracts;

import etiyaGameProje.entities.Gamer;

public interface GamerDao {
	
	void add(Gamer gamer);
	void list();
	void update(Gamer gamer);
	void remove(Gamer gamer);
}
