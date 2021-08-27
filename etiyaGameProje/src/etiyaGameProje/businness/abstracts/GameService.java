package etiyaGameProje.businness.abstracts;

import etiyaGameProje.entities.Game;

public interface GameService {
	
	void add(Game game);
	void update(Game game);
	void remove(Game game);
	void list();


}
