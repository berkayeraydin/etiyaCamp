package gameHomework.dataAccess.abstracts;

import gameHomework.entities.concretes.Game;

public interface GameDao {
	
	void add(Game game);
	void list();
	void update();
	void remove();

}
