package etiyaGameProje.businness.concretes;

import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class KidsGameCalculator extends  CalculateGameManager{
	
	@Override
	public void calculate(Gamer gamer, Game game) {
		if((2021 - gamer.getYearOfBirth()) < 18) {
			System.out.println(gamer.getFirstName() +" oyuncusu cocuktur. "+ game.getGameName() +" oyununun puanlarininin "
					+ "tamamini alir. Oyun puani = "+game.getGamePoint() +" , Puaniniz = "+ game.getGamePoint());
		}else {
			System.out.println(" Yasiniz 18 den buyuk, Çocuk degilsiniz");
		}
	}
}
