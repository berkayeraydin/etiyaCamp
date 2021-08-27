package etiyaGameProje.businness.concretes;

import java.util.ArrayList;

import etiyaGameProje.businness.abstracts.UserCheckService;
import etiyaGameProje.entities.User;

public class UserCheckManager implements UserCheckService {

	ArrayList<User> users = new ArrayList<User>();
	
	
	@Override
	public boolean checkFirstName(User user) {
		if(user.getFirstName().length()<3) {
			System.out.println("Isim 2 karakterden fazla olmalidir.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkLastName(User user) {
		if(user.getLastName().length()<3) {
			System.out.println("Soyisim 2 karakterden fazla olmalidir.");
			return false;
		}
		return true;
	}

	@Override
	public boolean checkNationalIdentityNumber(User user) {
		if(user.getNationalIdentityNumber().length()<11) {
			System.out.println("Kimlik Numarasi 11 haneli olmalidir");
			return false;
		}
		return true;
	}

	@Override
	public boolean approval(User user) {
		if(checkFirstName(user) && checkLastName(user)&& checkNationalIdentityNumber(user) == true) {
			return true;
		}
		return false;
	}
	
	
}