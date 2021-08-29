package etiyaGameProje.businness.concretes;

import etiyaGameProje.businness.abstracts.BaseCalculateGameService;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class CalculateGameManager implements BaseCalculateGameService {

	@Override
	public void calculate(Gamer gamer, Game game) {
		
		if ((2021 - gamer.getYearOfBirth()) >70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yaslidir. "+ game.getGameName() +" oyununun puaninin "
					+ "%90 ini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.9);
		}else if ((2021 - gamer.getYearOfBirth()) >=18) {
			if((2021 - gamer.getYearOfBirth()) >=18 && (2021 - gamer.getYearOfBirth()) <=70) {
				System.out.println(gamer.getFirstName() +" oyuncusu yetiskindir. "
						+ game.getGameName() + " oyununun puaninin %70 ini alir. Oyun puani = " +game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.7);
			}
		}else if( (2021 - gamer.getYearOfBirth()) >0 && (2021 - gamer.getYearOfBirth()) < 18) {
			System.out.println(gamer.getFirstName() +" oyuncusu cocuktur. "+ game.getGameName() +" oyununun puanlarininin "
					+ "tamamini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint());
		}
	}
	
}
