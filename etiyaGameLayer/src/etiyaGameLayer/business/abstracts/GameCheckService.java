package etiyaGameLayer.business.abstracts;

import etiyaGameLayer.entities.concretes.Game;

public interface GameCheckService {
	
	boolean checkGameName(Game game);
	boolean checkGamePrice(Game game);
	boolean checkGamePoint(Game game);
	boolean approval(Game game);
}
