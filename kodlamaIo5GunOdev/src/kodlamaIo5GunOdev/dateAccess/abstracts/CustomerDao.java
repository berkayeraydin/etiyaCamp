package kodlamaIo5GunOdev.dateAccess.abstracts;



import kodlamaIo5GunOdev.entities.concretes.Customer;

public interface CustomerDao {
	
	void add(Customer customer);
	void remove(Customer customer);
	boolean getEmail(String email);
	boolean getPassword(String password);
	

}
