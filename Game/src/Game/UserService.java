package Game;

import java.util.ArrayList;

public class UserService {
	
	ArrayList<User> users = new ArrayList<User>();
	
	public void userAdd(User user) {
		if (user.getUserAge() >= 15 ) {
			users.add(user);
			System.out.println("Basariyla gerceklesti");
		}else {
			System.out.println("15 Yasindan kucuksun");
		}	
	}
	public ArrayList<User> getAllUsers() {
		
		return users;
	}
}
