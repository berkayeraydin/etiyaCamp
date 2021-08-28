package etiyaGameProje.dataAccess.abstracts;

import java.util.ArrayList;

import etiyaGameProje.entities.Game;

public interface GameDao {
	
	void add(Game game);
	ArrayList<Game> list();
	void update(Game game);
	void remove(Game game);
}
