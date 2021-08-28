package solidYoutube.SRP;

public class CustomerManager {
	
	// Aspect oriented programming
	public void transactionalOperation() {
		
		//Loglama
		//Validation
		//Caching
		//Auth
		//Performance
		insert();
		update();
	}
	
	// Disaridan erisilebilir olmasini istemiyorsan "private" yapilabilir
	public void insert() {
		MyContext context = new MyContext();
		context.insert();
	}
	
	public void update() {
		MyContext context = new MyContext();
		context.update();
	}
	
	

}
