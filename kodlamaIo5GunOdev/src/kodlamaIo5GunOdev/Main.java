package kodlamaIo5GunOdev;

import kodlamaIo5GunOdev.business.concretes.CustomerCheckManager;
import kodlamaIo5GunOdev.business.concretes.CustomerManager;
import kodlamaIo5GunOdev.business.concretes.VerificationManager;
import kodlamaIo5GunOdev.dateAccess.concretes.HibernateCustomerDao;
import kodlamaIo5GunOdev.entities.concretes.Customer;

public class Main {

	public static void main(String[] args) {
		
		Customer c1 = new Customer(1,"Berkay","ERAYDIN","berkayeraydin@outlook.com","1234567");
		Customer c2 = new Customer(1,"Sena","ERAYDIN","senaeraydinoutlook.com","1234567");
		Customer c3 = new Customer(1,"Serkan","e","serkaneraydin@outlook.com","1234567");
		Customer c4 = new Customer(1,"Sevgi","ERAYDIN","sevgieraydin@outlook.com","12");
		
		
		CustomerManager customerManager = new CustomerManager(new CustomerCheckManager(),new VerificationManager(),new HibernateCustomerDao());
		
		customerManager.signUp(c1);
		customerManager.signUp(c2);
		customerManager.signUp(c3);
		customerManager.signUp(c4);
		customerManager.signUp(c1);
		customerManager.login(c1);
	}

}
