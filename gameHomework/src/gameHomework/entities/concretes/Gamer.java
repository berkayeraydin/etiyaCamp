package gameHomework.entities.concretes;

import gameHomework.entities.abstracts.Entity;

public class Gamer implements Entity {
	
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private String yearOfBirth;
	
	public Gamer() {
		super();
	}

	@Override
	public String toString() {
		return "Gamer [firstName=" + firstName + ", lastName=" + lastName + ", nationalIdentity=" + nationalIdentity
				+ ", yearOfBirth=" + yearOfBirth + "]";
	}

	public Gamer(String firstName, String lastName, String nationalIdentity, String yearOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalIdentity = nationalIdentity;
		this.yearOfBirth = yearOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationalIdentity() {
		return nationalIdentity;
	}

	public void setNationalIdentity(String nationalIdentity) {
		this.nationalIdentity = nationalIdentity;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	
	

}
