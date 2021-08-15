package kodlamaIo5GunOdev.business.concretes;

import java.util.ArrayList;
import java.util.regex.Pattern;

import kodlamaIo5GunOdev.business.abstracts.CustomerCheckService;
import kodlamaIo5GunOdev.entities.concretes.Customer;

public class CustomerCheckManager implements CustomerCheckService {

	ArrayList<String> listOfEmail = new ArrayList<String>();
	
	@Override
	public boolean checkFirstName(Customer customer) {
		//isEmpty true dondururse bunun anlami string uzunlugu 0
		if (customer.getFirstName().isEmpty()) {
			System.out.println("Isim alanini doldurunuz");
			return false;
		} else {
			if (customer.getFirstName().length() < 3) {
				System.out.println("Isim 2 karakterden fazla olmalidir.");
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean checkLastName(Customer customer) {
		// usttekinin aynisini soy isim icin yapiyoruz
		if (customer.getLastName().isEmpty()) {
			System.out.println("Soyisim alanini doldurunuz");
			return false;
		} else {
			if (customer.getLastName().length() < 3) {
				System.out.println("Soyisim 2 karakterden fazla olmalidir.");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkEmail(Customer customer) {
		String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		if (customer.getEmail().isEmpty()) {
			System.out.println("Email alanini doldurunuz.");
			return false;
		} else {
			if (pattern.matcher(customer.getEmail()).find() == false) {
				System.out.println("Girilen email adresi uygunsuz.");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean checkPassword(Customer customer) {
		if (customer.getPassword().isEmpty()) {
			System.out.println("Sifre alanini doldurunuz.");
			return false;
		}else {
			if (customer.getPassword().length() < 6) {
				System.out.println("Sifre 6 karakterden daha buyuk olmali.");
				return false;
			}
		}
		return true;
	}


	@Override
	public boolean uniqueEmail(Customer customer) {
		boolean result=true;
		//MAIL LISTESININ ICINDE GIRILEN MAIL VAR MI KONTROL
		if (listOfEmail.contains(customer.getEmail())) {
			System.out.println("Mail adresi kayitli. Baska mail giriniz.");
			result=false;
		}else{
			// GIRILEN MAILI EKLIYOR
			result=true;
			listOfEmail.add(customer.getEmail());
		}		
		return result;
	}

	@Override
	// tum kosullar true ise onay ver
	public boolean approval(Customer customer) {
		if (checkFirstName(customer) && checkLastName(customer) && checkEmail(customer) && checkPassword(customer)
				&& uniqueEmail(customer) == true) {
			return true;
		}
		return false;
	}

}
