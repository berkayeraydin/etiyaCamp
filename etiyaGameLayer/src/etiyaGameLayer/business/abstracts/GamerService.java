package etiyaGameLayer.business.abstracts;

import java.util.List;

import etiyaGameLayer.entities.concretes.Gamer;

public interface GamerService {
	void add(Gamer gamer);
	void update(Gamer gamer);
	void remove(Gamer gamer);
	List<Gamer> getAll();
	
}
