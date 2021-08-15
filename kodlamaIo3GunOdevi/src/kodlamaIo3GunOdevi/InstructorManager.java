package kodlamaIo3GunOdevi;

public class InstructorManager extends UserManager {
	
	public void allInstructors(Instructor[] instructors) {
		for(Instructor instructor:instructors) {
			System.out.println("Id : "+instructor.getId());
			System.out.println("Ismi : "+instructor.getFirstName());
			System.out.println("Soyadi : "+instructor.getLastName());
			System.out.println("Yasi : "+instructor.getAge());
			System.out.println("Kursu : "+instructor.getCourse());
			System.out.println("Maasi :"+instructor.getPrice());
			System.out.println("--------------");
			
		}
	}
}