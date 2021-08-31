package etiyaGameProje.businness.concretes;

import java.time.LocalDate;
import java.time.Period;

import etiyaGameProje.businness.abstracts.BaseCalculateGameService;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class WomenGameCalculator implements BaseCalculateGameService {

	
	@Override
	public double calculate(Gamer gamer,Game game) {
		Period ageDifference = Period.between(gamer.getYearOfBirth(), LocalDate.now());
		if(ageDifference.getYears() >=18 && ageDifference.getYears() <=70 ) {
			System.out.println(gamer.getFirstName() +" oyuncusu yetiskin bir kadindir. "
					+ game.getGameName() + " oyununun puaninin %70 ini alir. Oyun puani = " +game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.7);
			return game.getGamePoint()*0.7;
		}else {
			System.out.println("Yetiskin degilsiniz.");
			return 0;
		}

	}
}
