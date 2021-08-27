package etiyaGameProje.businness.concretes;

import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class WomenGameCalculator extends CalculateGameManager {
	
	@Override
	public double calculate(Gamer gamer,Game game) {
		if((2021 - gamer.getYearOfBirth()) >=18 && (2021 - gamer.getYearOfBirth()) <=70) {
			System.out.println(gamer.getFirstName() +" oyuncusu. Yetiskin bir kadin oyunun %70 ini alir. Puaniniz = "+ game.getGamePoint()*0.7);
			return game.getGamePoint()*0.7;
		}else {
			System.out.println("Yetiskin degilsiniz.");
		}
		
		return game.getGamePoint()*0.7;
	}
}
