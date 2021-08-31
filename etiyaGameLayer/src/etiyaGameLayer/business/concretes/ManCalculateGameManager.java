package etiyaGameLayer.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import etiyaGameLayer.business.abstracts.CalculateGameService;
import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public class ManCalculateGameManager implements CalculateGameService {

	@Override
	public double calculate(Gamer gamer, Game game) {
		Period ageDifference = Period.between(gamer.getYearOfBirth(), LocalDate.now());
		if(ageDifference.getYears() >=18 && ageDifference.getYears() <=70 ) {
			System.out.println(gamer.getFirstName() +" oyuncusu yetiskin bir erkektir. "
					+ game.getGameName() + " oyununun puaninin %70 ini alir. Oyun puani = " +game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.7);
			return game.getGamePoint()*0.7;
		}else {
			System.out.println("Yetiskin degilsiniz.");
			return 0;
		}
	}
	
}
