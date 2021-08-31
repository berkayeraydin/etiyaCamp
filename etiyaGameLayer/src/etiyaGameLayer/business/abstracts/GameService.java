package etiyaGameLayer.business.abstracts;

import java.util.List;

import etiyaGameLayer.entities.concretes.Game;

public interface GameService {
	
	void add(Game game);
	void update(Game game);
	void remove(Game game);
	List<Game> getAll();
}
