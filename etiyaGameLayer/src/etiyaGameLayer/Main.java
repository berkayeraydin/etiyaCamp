package etiyaGameLayer;

import java.time.LocalDate;

import etiyaGameLayer.business.abstracts.GamePlayService;
import etiyaGameLayer.business.concretes.CampaingManager;
import etiyaGameLayer.business.concretes.GameCheckManager;
import etiyaGameLayer.business.concretes.GameManager;
import etiyaGameLayer.business.concretes.GamePlayManager;
import etiyaGameLayer.business.concretes.GamerCheckManager;
import etiyaGameLayer.business.concretes.GamerManager;
import etiyaGameLayer.business.concretes.KidsCalculateGameManager;
import etiyaGameLayer.business.concretes.ManCalculateGameManager;
import etiyaGameLayer.business.concretes.OldCalculateGameManager;
import etiyaGameLayer.business.concretes.SellingManager;
import etiyaGameLayer.core.MernisKpsServiceAdapter;
import etiyaGameLayer.dataAccess.concretes.hibernate.HibernateCampaingDao;
import etiyaGameLayer.dataAccess.concretes.hibernate.HibernateGamerDao;
import etiyaGameLayer.dataAccess.concretes.hibernate.HibernateSellingDao;
import etiyaGameLayer.dataAccess.concretes.jdbc.JdbcCampaingDao;
import etiyaGameLayer.dataAccess.concretes.jdbc.JdbcGameDao;
import etiyaGameLayer.dataAccess.concretes.jdbc.JdbcSellingDao;
import etiyaGameLayer.entities.concretes.Campaing;
import etiyaGameLayer.entities.concretes.Game;
import etiyaGameLayer.entities.concretes.Gamer;
import etiyaGameLayer.entities.concretes.Selling;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("----------KAMPANYA ISLEMLERI----------------");
		Campaing campaing1 = new Campaing(1,"Yaz Indirimi",50);
		CampaingManager campaingManager = new CampaingManager(new HibernateCampaingDao());
		CampaingManager campaingManager2 = new CampaingManager(new JdbcCampaingDao());
		campaingManager.add(campaing1);
		campaingManager2.add(campaing1);
		
		System.out.println();
		System.out.println("----------OYUNCU ISLEMLERI----------------");
		Gamer gamer1 = new Gamer(1,"234512311161","Engin","DEMIROG",LocalDate.of(1985, 8, 29),"Erkek");
		Gamer gamer2 = new Gamer(2,"234512311161","Berkay","ERAYDIN",LocalDate.of(1920, 10, 27),"Erkek");
		Gamer gamer3 = new Gamer(3,"234512311161","Sena","ERAYDIN",LocalDate.of(2009, 3, 3),"Kadin");
		GamerManager gamerManager = new GamerManager(new MernisKpsServiceAdapter(),new GamerCheckManager(),new HibernateGamerDao());
		gamerManager.add(gamer1);
		gamerManager.add(gamer2);
		gamerManager.add(gamer3);
		for (Gamer gamerIndex : gamerManager.getAll()) {
			System.out.println(gamerIndex);
		}
		
		System.out.println();
		System.out.println("----------OYUN EKLEME ISLEMLERI----------------");
		Game game1 = new Game(1,"CS","FPS",50,100);
		GameManager gameManager = new GameManager(new JdbcGameDao(),new GameCheckManager());
		gameManager.add(game1);
		gameManager.update(game1);
		gamerManager.remove(gamer3);
		
		System.out.println();
		System.out.println("----------SATIS ISLEMLERI----------------");
		Selling selling = new Selling(1,gamer1,game1,campaing1);
		Selling selling2 = new Selling(2,gamer2,game1);
		SellingManager sellingManager = new SellingManager(new JdbcSellingDao());
		sellingManager.campaingSales(selling);
		sellingManager.sales(selling2);
		for (Selling sellingIndex : sellingManager.getAll()) {
			System.out.println(sellingIndex);
		}
		
		System.out.println();
		System.out.println("-------OYUN OYNATMA PUAN----------");
		// BURADA API DE YAZILACAK IF KOSULLARI YAZDIGIMIZI VAR SAYALIM
		GamePlayService gamePlayService  = new GamePlayManager(new ManCalculateGameManager());
		GamePlayService gamePlayService2 = new GamePlayManager(new OldCalculateGameManager());
		GamePlayService gamePlayService3 = new GamePlayManager(new KidsCalculateGameManager());
		
		gamePlayService.play(gamer1,game1);
		gamePlayService2.play(gamer2,game1);
		gamePlayService3.play(gamer3, game1);
	
		
	}

}
