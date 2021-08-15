package kodlamaIo4GunOdev3.concrete;

import kodlamaIo4GunOdev3.Abstract.GamerCheckService;
import kodlamaIo4GunOdev3.entitiy.Gamer;

public class GamerCheckManager implements GamerCheckService{

	@Override
	public boolean CheckIfRealPerson(Gamer gamer) {
		
		return true;
	}

}
