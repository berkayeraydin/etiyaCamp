package etiyaGameLayer.business.abstracts;

import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public interface CalculateGameService {
	
	double calculate(Gamer gamer, Game game) ;
}
