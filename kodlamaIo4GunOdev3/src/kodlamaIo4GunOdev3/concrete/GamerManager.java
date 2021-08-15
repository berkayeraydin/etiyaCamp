package kodlamaIo4GunOdev3.concrete;

import kodlamaIo4GunOdev3.Abstract.GamerCheckService;
import kodlamaIo4GunOdev3.Abstract.GamerService;
import kodlamaIo4GunOdev3.entitiy.Gamer;

public class GamerManager implements GamerService{

	GamerCheckService gamerCheckService;
	
	public GamerManager(GamerCheckService gamerCheckService) {
		this.gamerCheckService = gamerCheckService;
	}
	
	
	// eDevletServisinde islemi yapiyor. return true dondugunden oyuncu eklendi
	@Override
	public void add(Gamer gamer) {
		if(gamerCheckService.CheckIfRealPerson(gamer)) {
			System.out.println("oyuncu eklendi");
		}else {
			System.out.println("oyuncu eklenmedi");
		}
		
		
	}

	@Override
	public void update(Gamer gamer) {
		System.out.println("Oyuncu istek uzerine guncellendi : "+gamer.getFirstName());
		
	}

	@Override
	public void delete(Gamer gamer) {
		System.out.println("Oyuncu istek uzerine silindi : "+gamer.getFirstName());
		
	}

}
