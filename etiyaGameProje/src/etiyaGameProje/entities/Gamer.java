package etiyaGameProje.entities;

public class Gamer extends User {
	
	private String gender;
	
	@Override
	public String toString() {
		return "Gamer [gender=" + gender + "]";
	}

	public Gamer() {
		
	}
	
	public Gamer(int id, String nationalIdentityNumber, String firstName, String lastName, int yearOfBirth, String gender) {
		super(id,nationalIdentityNumber,firstName,lastName,yearOfBirth);
		this.gender=gender;
	
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
