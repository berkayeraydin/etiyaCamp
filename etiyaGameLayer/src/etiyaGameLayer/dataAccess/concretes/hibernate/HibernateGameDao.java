package etiyaGameLayer.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.GameDao;
import etiyaGameLayer.entities.concretes.Game;

public class HibernateGameDao implements GameDao {
	
	List<Game> games = new ArrayList<Game>();
	
	@Override
	public List<Game> getAll() {
	
		return this.games;
	}

	@Override
	public void add(Game entity) {
		System.out.println(entity.getGameName() + " HibernateGameDao ile Eklendi");
		this.games.add(entity);
		
	}

	@Override
	public void update(Game entity) {
		System.out.println(entity.getGameName() + " HibernateGameDao ile Guncellendi");
		
	}

	@Override
	public void delete(Game entity) {
		System.out.println(entity.getGameName() + " HibernateGameDao ile Silindi");
		
	}

}
