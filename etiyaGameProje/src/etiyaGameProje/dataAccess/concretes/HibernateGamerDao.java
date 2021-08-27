package etiyaGameProje.dataAccess.concretes;

import etiyaGameProje.dataAccess.abstracts.GamerDao;
import etiyaGameProje.entities.Gamer;

public class HibernateGamerDao implements GamerDao {
	
	@Override
	public void add(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Eklendi");
		
	}

	@Override
	public void list() {
		System.out.println( " HibernateGamerDao ile Listelendi");
		
	}

	@Override
	public void update(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Guncellendi");
		
	}

	@Override
	public void remove(Gamer gamer) {
		System.out.println(gamer.getFirstName() + " HibernateGamerDao ile Silindi");
		
	}
	
	

}
