package kodlamaIo3GunOdevi;

public class Main {

	public static void main(String[] args) {
		
		Instructor instructor1 = new Instructor(1,"Engin","DEMIROG",30,"EtiyaCamp",2000);
		
		Instructor instructor2 = new Instructor(2,"Suat","OZCELIK",25,"EtiyaCamp",2000);
		
		Student student1 = new Student(1,"Berkay","ERAYDIN",23,01); 

		Student student2 = new Student(2,"Sena","ERAYDIN",19,02); 
		
		UserManager  userManager = new UserManager();
		InstructorManager instructorManager = new InstructorManager();
		StudentManager studentManager = new StudentManager();
		//array olustu
		Instructor[] instructor = {instructor1,instructor2};
		Student[] student = {student1,student2};
		//miras alarak UserMAnager deki fonksiyonu kullandık
		userManager.add(instructor1);
		userManager.add(instructor2);
		userManager.add(student1);
		userManager.add(student2);
		System.out.println("--------------");
		studentManager.remove(student1);
		System.out.println("--------------");
		
		instructorManager.allInstructors(instructor);
		studentManager.allSutudents(student);
	}
}

