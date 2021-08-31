package etiyaGameLayer.business.abstracts;

import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public interface GamePlayService {
	void play(Gamer gamer, Game game);
}
