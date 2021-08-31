package etiyaGameLayer.dataAccess.concretes.jdbc;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.GameDao;
import etiyaGameLayer.entities.concretes.Game;

public class JdbcGameDao implements GameDao {
	
	List<Game> games = new ArrayList<Game>();
	
	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		return this.games;
	}

	@Override
	public void add(Game entity) {
		System.out.println(entity.getGameName() + " JbcGameDao ile Eklendi");
		this.games.add(entity);
		
	}

	@Override
	public void update(Game entity) {
		System.out.println(entity.getGameName() + " JbcGameDao ile Guncellendi");
		
	}

	@Override
	public void delete(Game entity) {
		System.out.println(entity.getGameName() + " JbcGameDao ile Silindi");
		
	}

}
