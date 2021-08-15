package kodlamaIo4GunOdev3.concrete;

import kodlamaIo4GunOdev3.Abstract.GameService;
import kodlamaIo4GunOdev3.entitiy.Game;

public class GameManager implements GameService{

	@Override
	public void add(Game game) {
		System.out.println(game.getGameName() + " oyunu, "+game.getPrice()+ " fiyatiyla eklendi.");
		
	}

	@Override
	public void update(Game game) {
		System.out.println(game.getGameName()+ " oyunu basariyla güncellendi.");
		
	}

	@Override
	public void delete(Game game) {
		System.out.println(game.getPrice()+" degerindeki,"+game.getGameName()+" adli oyun silindi.");
		
	}

}
