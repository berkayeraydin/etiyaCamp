package etiyaGameProje.businness.abstracts;

import etiyaGameProje.entities.Game;

public interface GameCheckService {
	
	boolean checkGameName(Game game);
	boolean checkGamePrice(Game game);
	boolean checkGamePoint(Game game);
	boolean approval(Game game);
	

}
