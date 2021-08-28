package etiyaGameProje.dataAccess.abstracts;

import java.util.ArrayList;

import etiyaGameProje.entities.Gamer;

public interface GamerDao {
	
	void add(Gamer gamer);
	ArrayList<Gamer> list();
	void update(Gamer gamer);
	void remove(Gamer gamer);
}
