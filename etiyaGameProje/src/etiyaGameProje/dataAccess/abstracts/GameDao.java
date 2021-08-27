package etiyaGameProje.dataAccess.abstracts;

import etiyaGameProje.entities.Game;

public interface GameDao {
	
	void add(Game game);
	void list();
	void update(Game game);
	void remove(Game game);
}
