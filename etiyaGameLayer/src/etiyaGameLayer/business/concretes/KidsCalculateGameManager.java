package etiyaGameLayer.business.concretes;

import java.time.LocalDate;
import java.time.Period;

import etiyaGameLayer.business.abstracts.CalculateGameService;
import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;

public class KidsCalculateGameManager implements CalculateGameService{
	
	@Override
	public double calculate(Gamer gamer, Game game) {
		Period ageDifference = Period.between(gamer.getYearOfBirth(), LocalDate.now());
		if( ageDifference.getYears() >0 && ageDifference.getYears() < 18) {
			System.out.println(gamer.getFirstName() +" oyuncusu cocuktur. "+ game.getGameName() +" oyununun puanlarininin "
					+ "tamamini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint());
			return game.getGamePoint();
		}else {
			System.out.println(" Yasiniz 18 den buyuk, Çocuk degilsiniz");
			return 0;
		}
	}
}
