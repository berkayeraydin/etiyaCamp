package solidYoutube.OCP;

//Loosely Cuupled
public class CustomerManager implements CustomerService{
	
	CustomerDalService customerDalService ;
	
	public CustomerManager(CustomerDalService customerDalService) {
		super();
		this.customerDalService = customerDalService;
	}
	
	public void add() {
		customerDalService.add();
		
	}
	
}
