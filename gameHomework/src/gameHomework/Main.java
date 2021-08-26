package gameHomework;

import gameHomework.business.concretes.CampaingManager;
import gameHomework.business.concretes.GameManager;
import gameHomework.business.concretes.GamerManager;
import gameHomework.business.concretes.SellingManager;
import gameHomework.business.concretes.VerificationManager;
import gameHomework.dataAccess.concretes.HibernateCampaingDao;
import gameHomework.dataAccess.concretes.HibernateGameDao;
import gameHomework.dataAccess.concretes.HibernateGamerDao;
import gameHomework.dataAccess.concretes.HibernateSellingDao;
import gameHomework.entities.concretes.Campaing;
import gameHomework.entities.concretes.Game;
import gameHomework.entities.concretes.Gamer;
import gameHomework.entities.concretes.Selling;

public class Main {

	public static void main(String[] args) {
		
		Gamer gamer1 = new Gamer("Berkay","ERAYDIN","12345678900","1998");
		Gamer gamer2 = new Gamer("Serkan","ERAYDIN","12345678900","1998");
		
		GamerManager gamerManager = new GamerManager(new VerificationManager(),new HibernateGamerDao());
		
		gamerManager.register(gamer1);
		System.out.println("-------------");
		gamerManager.remove(gamer1);
		
		Campaing campaing1 = new Campaing(1,"Yaz Indirimi",50);
		
		CampaingManager campaingManager = new CampaingManager(new HibernateCampaingDao());
		campaingManager.add(campaing1);
		
		Game game1 = new Game(1,"CS",100);
		//Game game2 = new Game(1,"PUBG",70);
		System.out.println();
		System.out.println("------OYUN-------");
		
		GameManager gameManager = new GameManager(new HibernateGameDao());
		gameManager.add(game1);
		
		System.out.println();
		System.out.println("------SATIS-------");
		
		Selling selling = new Selling(1,gamer1,game1,campaing1);
		//Selling selling2 = new Selling(2,gamer2,game2,campaing1);
		
		SellingManager sellingManager = new SellingManager(new HibernateSellingDao());
		
		sellingManager.sales(selling);
		//sellingManager.sales(selling2);
		
		for (Selling selling1 : sellingManager.sellingList()) {
			System.out.println(selling1);
		}
		
		
	}

}
