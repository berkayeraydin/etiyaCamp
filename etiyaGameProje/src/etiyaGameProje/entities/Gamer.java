package etiyaGameProje.entities;

public class Gamer extends User {
	
	
	@Override
	public String toString() {
		return "Gamer [" + super.toString() +  "]";
	}

	public Gamer() {
		
	}
	
	public Gamer(int id, String nationalIdentityNumber, String firstName, String lastName, int yearOfBirth, String gender) {
		super(id,nationalIdentityNumber,firstName,lastName,yearOfBirth,gender);
		
	
	}

	
}
