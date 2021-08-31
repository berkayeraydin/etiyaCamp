package etiyaGameLayer.business.concretes;

import etiyaGameLayer.business.abstracts.CalculateGameService;
import etiyaGameLayer.business.abstracts.GamePlayService;
import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public class GamePlayManager implements GamePlayService{
	
	CalculateGameService calculateGameSercice;
	
	public GamePlayManager(CalculateGameService calculateGameSercice) {
		super();
		this.calculateGameSercice = calculateGameSercice;
	}
	
	
	@Override
	public void play(Gamer gamer, Game game) {
			
		this.calculateGameSercice.calculate(gamer,game);
				
	}
}
