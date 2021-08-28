package etiyaGameProje.dataAccess.concretes;

import java.util.ArrayList;

import etiyaGameProje.dataAccess.abstracts.GamerDao;
import etiyaGameProje.entities.Gamer;

public class HibernateGamerDao implements GamerDao {
	
	ArrayList<Gamer> gamer = new ArrayList<Gamer>();
	
	@Override
	public void add(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Eklendi");
		this.gamer.add(gamer);
	}

	@Override
	public void update(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Guncellendi");
		
	}

	@Override
	public void remove(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Silindi");
		
	}


	@Override
	public ArrayList<Gamer> list() {
		
		return gamer;
	}
	
	

}
