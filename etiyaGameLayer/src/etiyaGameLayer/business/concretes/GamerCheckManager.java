package etiyaGameLayer.business.concretes;

import etiyaGameLayer.business.abstracts.GamerCheckService;
import etiyaGameLayer.entities.concretes.Gamer;

public class GamerCheckManager implements GamerCheckService {
	
	@Override
	public boolean checkFirstName(Gamer gamer) {
		if(gamer.getFirstName().length()<3) {
			System.out.println("Isim 2 karakterden fazla olmalidir.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkLastName(Gamer gamer) {
		if(gamer.getLastName().length()<3) {
			System.out.println("Soyisim 2 karakterden fazla olmalidir.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkNationalIdentityNumber(Gamer gamer) {
		if(gamer.getNationalIdentityNumber().length()<11) {
			System.out.println("Kimlik Numarasi 11 haneli olmalidir");
			return false;
		}
		return true;
	}

	@Override
	public boolean approval(Gamer gamer) {
		if(checkFirstName(gamer) && checkLastName(gamer)&& checkNationalIdentityNumber(gamer) == true) {
			return true;
		}
		return false;
	}
}
