package kodlamaIo4GunOdev3.adapters;

import kodlamaIo4GunOdev3.Abstract.GamerCheckService;
import kodlamaIo4GunOdev3.entitiy.Gamer;

public class eDevletService implements GamerCheckService {

	@Override
	public boolean CheckIfRealPerson(Gamer gamer) {
		System.out.println(gamer.getFirstName()+" adli kisi E devlet tarafindan dogrulandi.");
		return true;
	}

}
