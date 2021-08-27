package etiyaGameProje.entities;

public class User {
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nationalIdentityNumber=" + nationalIdentityNumber + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", yearOfBirth=" + yearOfBirth + "]";
	}

	private int id;
	private String nationalIdentityNumber;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	
	public User() {
		super();
	}
	
	public User(int id, String nationalIdentityNumber, String firstName, String lastName, int yearOfBirth) {
		super();
		this.id = id;
		this.nationalIdentityNumber = nationalIdentityNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNationalIdentityNumber() {
		return nationalIdentityNumber;
	}

	public void setNationalIdentityNumber(String nationalIdentityNumber) {
		this.nationalIdentityNumber = nationalIdentityNumber;
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

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
}
