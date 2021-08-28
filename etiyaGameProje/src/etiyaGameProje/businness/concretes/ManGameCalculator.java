package etiyaGameProje.businness.concretes;

import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class ManGameCalculator extends CalculateGameManager{

	@Override
	public void calculate(Gamer gamer,Game game) {
		if((2021 - gamer.getYearOfBirth()) >=18 && (2021 - gamer.getYearOfBirth()) <=70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yetiskindir. "
					+ game.getGameName() + " oyununun puaninin %70 ini alir. Oyun puani = " +game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint()*0.7);
		}else {
			System.out.println("yetiskin degilsiniz.");
		}
	}
	
}
