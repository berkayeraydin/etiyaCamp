package etiyaGameProje.businness.abstracts;

import java.util.ArrayList;

import etiyaGameProje.entities.Gamer;

public interface GamerService {
	
	void add(Gamer gamer);
	void update(Gamer gamer);
	void remove(Gamer gamer);
	ArrayList<Gamer> gamerList();
}
