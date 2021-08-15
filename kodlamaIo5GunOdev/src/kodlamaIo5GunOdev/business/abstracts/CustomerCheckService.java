package kodlamaIo5GunOdev.business.abstracts;

import kodlamaIo5GunOdev.entities.concretes.Customer;

public interface CustomerCheckService {
	
	boolean checkFirstName(Customer customer);
	boolean checkLastName(Customer customer);
	boolean checkEmail(Customer customer);
	boolean checkPassword(Customer customer);
	boolean uniqueEmail(Customer customer);
	boolean approval(Customer customer);

}
