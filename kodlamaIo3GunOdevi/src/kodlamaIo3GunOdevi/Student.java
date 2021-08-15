package kodlamaIo3GunOdevi;

public class Student extends User {
	
	private int no;
	
	public Student(int id, String firstName, String lastName, int age, int no) {
		super(id, firstName, lastName, age);
		this.no = no;
	}
	public Student() {
		
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
}
