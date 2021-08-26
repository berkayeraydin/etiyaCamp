package gameHomework.business.abstracts;

import gameHomework.entities.concretes.Gamer;

public interface GamerService {

	void register(Gamer gamer);
	void update(Gamer gamer);
	void remove(Gamer gamer);
	
}
