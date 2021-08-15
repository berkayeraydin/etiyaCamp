package kodlamaIo4GunOdev3;

import kodlamaIo4GunOdev3.adapters.eDevletService;
import kodlamaIo4GunOdev3.concrete.CampaignManager;
import kodlamaIo4GunOdev3.concrete.GameManager;
import kodlamaIo4GunOdev3.concrete.GamerManager;
import kodlamaIo4GunOdev3.entitiy.Campaign;
import kodlamaIo4GunOdev3.entitiy.Game;
import kodlamaIo4GunOdev3.entitiy.Gamer;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		Gamer gamer=new Gamer(1,"Berkay","ERAYDIN",LocalDate.of(1998,10,27),"19040764506");
		
		Game game=new Game(1,"Fifa2021",500);
		
		Campaign campaign=new Campaign(1,"Yaz Kampanyasi",50,LocalDate.of(2021,8,14),LocalDate.of(2021,8,30));
		
		
		//GamerManager in icine eDevletService veya GamerChechService 
		//cunku  GamerManager da bana GamerCheckService gamerCheckService ver demisim
		// eDevletService oluyor cunku, eDevletService implements GamerCheckService
		GamerManager gamerManager=new GamerManager(new eDevletService());
		
		gamerManager.add(gamer);
		
		
		System.out.println("----------------------");
		CampaignManager campaignManager=new CampaignManager();
		campaignManager.add(campaign);
		campaignManager.update(campaign);
		campaignManager.delete(campaign);
		
		System.out.println("----------------------");
		GameManager gameManager=new GameManager();
		gameManager.add(game);
		gameManager.update(game);
		gameManager.delete(game);

	}

}
