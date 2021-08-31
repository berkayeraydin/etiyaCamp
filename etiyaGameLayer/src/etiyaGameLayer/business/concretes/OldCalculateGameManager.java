package etiyaGameLayer.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import etiyaGameLayer.business.abstracts.CalculateGameService;
import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public class OldCalculateGameManager implements CalculateGameService {

	@Override
	public double calculate(Gamer gamer, Game game) {
		Period ageDifference = Period.between(gamer.getYearOfBirth(), LocalDate.now());
		if (ageDifference.getYears() >70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yaslidir. "+ game.getGameName() +" oyununun puaninin "
					+ "%90 ini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.9);
			return game.getGamePoint()*0.9;
		}else {
			System.out.println("Yasli degilsiniz.");
			return 0;
		}
	}

}
