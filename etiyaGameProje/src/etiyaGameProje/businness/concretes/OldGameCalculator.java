package etiyaGameProje.businness.concretes;

import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;

public class OldGameCalculator extends CalculateGameManager{
	
	@Override
	public double calculate(Gamer gamer, Game game) {
		if ((2021 - gamer.getYearOfBirth()) >70) {
			System.out.println(gamer.getFirstName() +" oyuncusu yaslidir. "+ game.getGameName() +" oyununun puanlarininin "
					+ "%90 ini alir. Oyun puanı = "+game.getGamePoint() +" Puaniniz = "+ game.getGamePoint());
		}else {
			System.out.println("Yasli degilsiniz.");
		}
		
		return game.getGamePoint()*0.9;
	}
}
