package etiyaGameProje.dataAccess.concretes;

import etiyaGameProje.dataAccess.abstracts.GameDao;
import etiyaGameProje.entities.Game;

public class HibernateGameDao implements GameDao{

	@Override
	public void add(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Eklendi");
		
	}

	@Override
	public void list() {
		System.out.println(" HibernateGameDao ile Listelendi");
		
	}

	@Override
	public void update(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Güncellendi");
		
	}

	@Override
	public void remove(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Silindi");
		
	}

}
