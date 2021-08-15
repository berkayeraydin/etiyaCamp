package kodlamaIo5GunOdev.dateAccess.concretes;

import java.util.ArrayList;

import kodlamaIo5GunOdev.dateAccess.abstracts.CustomerDao;
import kodlamaIo5GunOdev.entities.concretes.Customer;

public class HibernateCustomerDao implements CustomerDao {
	
	
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	@Override
	// add fonksiyonu cagrildiginda customers listesine ekle
	public void add(Customer customer) {
		this.customers.add(customer);
		System.out.println("Hibernate ile eklendi : " +customer.getFirstName());
		
	}

	@Override
	public void remove(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// customers listesinde mustteri emaili bulma
	public boolean getEmail(String email) {
		for (Customer customer : customers) {
			if(customer.getEmail() == email) {
				return true;
			}
		}
		return false;	
	}

	@Override
	// customers listesinde mustteri sifresini bulma
	public boolean getPassword(String password) {
		for(Customer customer : customers) {
			if(customer.getPassword() == password)
			{
				return true;
			}
		}
		return false;
	}



}
