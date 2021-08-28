package etiyaGameProje.businness.concretes;

import etiyaGameProje.businness.abstracts.GameCheckService;
import etiyaGameProje.entities.Game;

public class GameCheckManager implements GameCheckService{

	@Override
	public boolean checkGameName(Game game) {
		if(game.getGameName().length() < 2) {
			System.out.println("Girdiginiz oyun 2 karakterden kucuk olamaz");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkGamePrice(Game game) {
		if(game.getGamePrice() <0) {
			System.out.println("Girilen oyun parasi 0 dan kucuk olamaz");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkGamePoint(Game game) {
		if(game.getGamePrice() <0) {
			System.out.println("Girilen oyun parasi 0 dan kucuk olamaz");
			return false;
		}
		return true;
	}

	@Override
	public boolean approval(Game game) {
		if(checkGameName(game) && checkGamePoint(game)&& checkGamePrice(game) == true) {
			return true;
		}
		return false;
	}

	

}
