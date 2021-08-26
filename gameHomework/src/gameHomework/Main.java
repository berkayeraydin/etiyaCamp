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
		
		Campaing campaing1 = new Campaing(1,"Yaz Indirimi",50);
		
		Gamer gamer1 = new Gamer("Berkay","ERAYDIN","12345678900","1998");
		Gamer gamer2 = new Gamer("Serkan","ERAYDIN","12345678900","1998");
		
		Game game1 = new Game(1,"CS",100,campaing1);
		Game game2 = new Game(2,"PUBG",70,campaing1);
		Game game3 = new Game(3,"VALORANT",10);
		
		GamerManager gamerManager = new GamerManager(new VerificationManager(),new HibernateGamerDao());
		gamerManager.register(gamer1);
		gamerManager.remove(gamer1);
		
		System.out.println("-------------");
		CampaingManager campaingManager = new CampaingManager(new HibernateCampaingDao());
		campaingManager.add(campaing1);
		
		System.out.println("------OYUN EKLEME-------");
		GameManager gameManager = new GameManager(new HibernateGameDao());
		gameManager.add(game1);
		gameManager.add(game3);
		
		System.out.println("------SATIS-------");
		SellingManager sellingManager = new SellingManager(new HibernateSellingDao());
		sellingManager.campaingSales(game1,gamer1);
		sellingManager.campaingSales(game2,gamer2);
		sellingManager.sales(game3, gamer2);
		
		for (Selling selling : sellingManager.sellingList()) {
			System.out.println(selling);
		}
		
		
	}

}
