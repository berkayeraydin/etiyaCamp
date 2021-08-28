package etiyaGameProje.businness.concretes;

import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class OldGameCalculator extends CalculateGameManager{
	
	@Override
	public void calculate(Gamer gamer, Game game) {
		if ((2021 - gamer.getYearOfBirth()) >70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yaslidir. "+ game.getGameName() +" oyununun puaninin "
					+ "%90 ini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.9);
		}else {
			System.out.println("Yasli degilsiniz.");
			
		}
		
	}
}
