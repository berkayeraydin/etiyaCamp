package kodlamaIo3GunOdevi;

public class Instructor extends User {
	
	private String course;
	private double price;

	public Instructor(int id, String firstName, String lastName, int age, String course, double price) {
		super(id, firstName, lastName, age);
		this.course = course;
		this.price = price;
	}
	
	public Instructor() {
		
	}
	
	public String getCourse() {
		return course;
	}

	public double getPrice() {
		return price;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
