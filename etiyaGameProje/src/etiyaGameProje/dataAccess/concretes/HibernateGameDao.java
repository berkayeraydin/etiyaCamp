package etiyaGameProje.dataAccess.concretes;

import java.util.ArrayList;

import etiyaGameProje.dataAccess.abstracts.GameDao;
import etiyaGameProje.entities.Game;

public class HibernateGameDao implements GameDao{
	
	ArrayList<Game> games = new ArrayList<Game>();
	
	@Override
	public void add(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Eklendi");
		this.games.add(game);
	}

	@Override
	public void update(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Güncellendi");
		
	}

	@Override
	public void remove(Game game) {
		System.out.println(game.getGameName() + " HibernateGameDao ile Silindi");
		this.games.remove(game);
	}


	@Override
	public ArrayList<Game> list() {
		
		return this.games;
	}

}
