package etiyaGameProje.businness.concretes;

import etiyaGameProje.businness.abstracts.BaseCalculateGameService;
import etiyaGameProje.businness.abstracts.GamePlayService;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class GamePlayManager implements GamePlayService {
	
	BaseCalculateGameService baseCalculateGameSercice;
	
	
	
	public GamePlayManager(BaseCalculateGameService baseCalculateGameSercice) {
		super();
		this.baseCalculateGameSercice = baseCalculateGameSercice;
	}
	
	
	@Override
	public void play(Gamer gamer, Game game) {
			
		
			this.baseCalculateGameSercice.calculate(gamer,game);
			
			
	}

	

}