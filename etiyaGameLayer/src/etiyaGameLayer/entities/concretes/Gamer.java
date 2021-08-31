package etiyaGameLayer.entities.concretes;

import java.time.LocalDate;

public class Gamer extends  User {
	
	@Override
	public String toString() {
		return "Gamer [" + super.toString() +  "]";
	}

	public Gamer() {
		
	}
	
	public Gamer(int id, String nationalIdentityNumber, String firstName, String lastName, LocalDate yearOfBirth, String gender) {
		super(id,nationalIdentityNumber,firstName,lastName,yearOfBirth,gender);
		
	
	}
}
