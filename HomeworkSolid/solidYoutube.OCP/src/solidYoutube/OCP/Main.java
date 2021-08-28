package solidYoutube.OCP;

public class Main {

	public static void main(String[] args) {
		
		//Ioc Container
		CustomerService customerManager = new CustomerManager(new DapperCustomerDal());
		customerManager.add();
 
	}

}
