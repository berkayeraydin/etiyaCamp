package gameHomework.dataAccess.concretes;

import gameHomework.dataAccess.abstracts.GamerDao;

public class HibernateGamerDao implements GamerDao {
	
	
	@Override
	// Gamer game
	public void add() {
		System.out.println("Hibernate ile oyuncu eklendi.");
		
	}

	@Override
	public void list() {
		System.out.println("Hibernate ile oyuncular listelendi.");
		
	}

	@Override
	public void update() {
		System.out.println("Hibernate ile oyuncu guncellendi.");
		
	}

	@Override
	public void remove() {
		System.out.println("Hibernate ile oyuncu silindi.");
		
	}

}
