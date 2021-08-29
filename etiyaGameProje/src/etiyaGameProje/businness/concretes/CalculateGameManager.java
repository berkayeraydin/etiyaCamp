package etiyaGameProje.businness.concretes;

import java.time.LocalDate;
import java.time.Period;

import etiyaGameProje.businness.abstracts.BaseCalculateGameService;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class CalculateGameManager implements BaseCalculateGameService {

	@Override
	public void calculate(Gamer gamer, Game game) {
		//https://stackoverflow.com/questions/31715958/android-eclipse-localdate-cannot-be-resolved-to-a-type
			
		Period ageDifference = Period.between(gamer.getYearOfBirth(), LocalDate.now());
		
		if (ageDifference.getYears() >70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yaslidir. "+ game.getGameName() +" oyununun puaninin "
					+ "%90 ini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.9);
			
		}else if (ageDifference.getYears() >=18) {
				System.out.println(gamer.getFirstName() +" oyuncusu yetiskindir. "
						+ game.getGameName() + " oyununun puaninin %70 ini alir. Oyun puani = " +game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.7);
				
		}else if( ageDifference.getYears() >0 && ageDifference.getYears() < 18) {
			System.out.println(gamer.getFirstName() +" oyuncusu cocuktur. "+ game.getGameName() +" oyununun puanlarininin "
					+ "tamamini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint());
		}
	}
	
}
