package kodlama.io.entities.concretes;

public class Instructor extends User {
	
	private int InstructorId;
	
	public Instructor() {
		
	}

	public Instructor(int InstructorId,int id, String firstName, String lastName, String mail ,String imagePath) {
		super(id,firstName,lastName,mail,imagePath);
		this.InstructorId = InstructorId;
	}
	
	@Override
	public String toString() {
		return "Instructor [InstructorId= " + InstructorId + " "+ super.toString() + "]";
	}
	
	

}

