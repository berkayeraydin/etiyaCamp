package gameHomework.business.abstracts;

import gameHomework.entities.concretes.Game;

public interface GameService {
	
	void add(Game game);
	void remove();
	void update();

}
