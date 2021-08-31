package etiyaGameLayer.dataAccess.concretes.jdbc;

import java.util.ArrayList;
import java.util.List;

import etiyaGameLayer.dataAccess.abstracts.GamerDao;
import etiyaGameLayer.entities.concretes.Gamer;

public class JdbcGamerDao implements GamerDao{
	
	List<Gamer> gamers = new ArrayList<Gamer>();
	
	@Override
	public List<Gamer> getAll() {
		// TODO Auto-generated method stub
		return this.gamers;
	}

	@Override
	public void add(Gamer entity) {
		System.out.println(entity.getFirstName() + " JdbcGamerDao ile Eklendi");
		this.gamers.add(entity);
		
	}

	@Override
	public void update(Gamer entity) {
		System.out.println(entity.getFirstName() + " JdbcGamerDao ile Guncellendi");
		
	}

	@Override
	public void delete(Gamer entity) {
		System.out.println(entity.getFirstName() + " JdbcGamerDao ile Silindi");
		
	}

}
