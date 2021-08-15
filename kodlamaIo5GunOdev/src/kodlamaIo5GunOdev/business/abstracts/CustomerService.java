package kodlamaIo5GunOdev.business.abstracts;

import kodlamaIo5GunOdev.entities.concretes.Customer;

public interface CustomerService {
	
	void signUp(Customer customer);
	void signIn(Customer customer);

}
