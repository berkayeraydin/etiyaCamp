package etiyaGame;

import java.util.ArrayList;

public class UserService {
	
	ArrayList<User> users = new ArrayList<User>();
	
	public void userAdd(User user) {
		if (user.getUserAge() >= 15 ) {
			users.add(user);	
		}else {
			System.out.println("15 Yasindan kucuksun. Eklenemedin.");
		}	
	}
	public ArrayList<User> getAllUsers() {
		
		// SANAL OLARAK DB
		
		/*User u1 = new User("Berkay","ERAYDIN",18);
		User u2 = new User("Sena","ERAYDIN",20);
		
		this.users.add(u1);
		this.users.add(u2);*/
		
		return users;
	}
}
