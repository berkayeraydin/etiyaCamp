package kodlamaIo3GunOdevi;

public class UserManager  {
	
	public void add(User user) {
		System.out.println(user.getFirstName() + "  ismiyle basariyla eklendiniz.");
	}
	
	public void remove(User user) {
		System.out.println(user.getFirstName() + " kisisi silindi.");
	}
	
	public void allUser(User[] users) {
		for(User user:users) {
			System.out.println("Id : "+user.getId());
			System.out.println("Ismi : "+user.getFirstName());
			System.out.println("Soyadi : "+user.getLastName());
			System.out.println("Yasi : " +user.getAge());
			System.out.println("--------------");
		}
	}
}