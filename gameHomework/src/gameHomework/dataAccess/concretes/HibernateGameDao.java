package gameHomework.dataAccess.concretes;

import gameHomework.dataAccess.abstracts.GameDao;
import gameHomework.entities.concretes.Game;

public class HibernateGameDao implements GameDao {

	
	@Override
	public void add(Game game) {
		System.out.println("Oyun hibernateGameDao ile eklendi ");
		
	}

	@Override
	// Game game
	public void list() {
		System.out.println("Oyunlar hibernateGameDao ile listelendi ");
		
	}

	@Override
	// Game game
	public void update() {
		System.out.println("Oyun hibernateGameDao ile guncellendi ");
		
	}

	@Override
	// Game game
	public void remove() {
		System.out.println("Oyun hibernateGameDao ile silindi ");
		
	}

}
