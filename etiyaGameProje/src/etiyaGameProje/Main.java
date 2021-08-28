package etiyaGameProje;

import etiyaGameProje.businness.abstracts.GamePlayService;
import etiyaGameProje.businness.abstracts.GameService;
import etiyaGameProje.businness.abstracts.GamerService;
import etiyaGameProje.businness.concretes.CalculateGameManager;
import etiyaGameProje.businness.concretes.CampaingManager;
import etiyaGameProje.businness.concretes.GameCheckManager;
import etiyaGameProje.businness.concretes.GameManager;
import etiyaGameProje.businness.concretes.GamePlayManager;
import etiyaGameProje.businness.concretes.GamerManager;
import etiyaGameProje.businness.concretes.KidsGameCalculator;
import etiyaGameProje.businness.concretes.ManGameCalculator;
import etiyaGameProje.businness.concretes.OldGameCalculator;
import etiyaGameProje.businness.concretes.SellingManager;
import etiyaGameProje.businness.concretes.UserCheckManager;
import etiyaGameProje.businness.concretes.WomenGameCalculator;
import etiyaGameProje.core.MernisKpsServiceAdapter;
import etiyaGameProje.dataAccess.concretes.HibernateCampaingDao;
import etiyaGameProje.dataAccess.concretes.HibernateGameDao;
import etiyaGameProje.dataAccess.concretes.HibernateGamerDao;
import etiyaGameProje.dataAccess.concretes.HibernateSellingDao;
import etiyaGameProje.entities.Campaing;
import etiyaGameProje.entities.Game;
import etiyaGameProje.entities.Gamer;
import etiyaGameProje.entities.Selling;

public class Main {

	public static void main(String[] args) {
		
		Campaing campaing1 = new Campaing(1,"Yaz Indirimi",50);
		
		Gamer gamer = new Gamer(1,"234512311161","Engin","DEMIROG",1985,"Erkek");
		Gamer gamer2 = new Gamer(2,"234512311161","Berkay","ERAYDIN",1920,"Erkek");
		Gamer gamer3 = new Gamer(3,"234512311161","Sena","ERAYDIN",2009,"Kadin");
		Gamer gamer4= new Gamer(4,"234512311161","Sena2","ERAYDIN",2009,"Kadin");
		
		Game game1 = new Game(1,"CS","FPS",50,100);
		
		System.out.println("--------KULLANICI KONTROLU VE DB EKLEME----------");
		GamerService gamerService = new GamerManager(new MernisKpsServiceAdapter(),new UserCheckManager(),new HibernateGamerDao());
		gamerService.add(gamer);
		
		for (Gamer gamerIndex : gamerService.gamerList()) {
			System.out.println(gamerIndex);
		}
		
		System.out.println("--------OYUN DB EKLEME----------");
		GameService gameService = new GameManager(new HibernateGameDao(),new GameCheckManager());
		gameService.add(game1);
		
		System.out.println("--------KAMPANYA DB EKLEME----------");
		CampaingManager campaingManager = new CampaingManager(new HibernateCampaingDao());
		campaingManager.add(campaing1);
		
		System.out.println("------SATIS-------");
		Selling selling = new Selling(1,gamer,game1,campaing1);
		Selling selling2 = new Selling(2,gamer2,game1);
		
		SellingManager sellingManager = new SellingManager(new HibernateSellingDao());
		
		sellingManager.campaingSales(selling);
		sellingManager.sales(selling2);
		
		for (Selling sellingIndex : sellingManager.sellingList()) {
			System.out.println(sellingIndex);
		}
		
		System.out.println();
		System.out.println("-------OYUN OYNATMA-----------");
		GamePlayService gamePlayService  = new GamePlayManager(new ManGameCalculator());
		GamePlayService gamePlayService2 = new GamePlayManager(new OldGameCalculator());
		GamePlayService gamePlayService3 = new GamePlayManager(new OldGameCalculator());
		GamePlayService gamePlayService4 = new GamePlayManager(new KidsGameCalculator());
		
		gamePlayService.play(gamer,game1);
		gamePlayService2.play(gamer2,game1);
		gamePlayService3.play(gamer3, game1);
		gamePlayService4.play(gamer4, game1);
		
	}

}