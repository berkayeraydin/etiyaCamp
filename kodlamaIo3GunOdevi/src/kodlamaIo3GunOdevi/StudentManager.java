package kodlamaIo3GunOdevi;

public class StudentManager extends UserManager {
	
	public void allSutudents(Student[] students) {
		for(Student student:students) {
			System.out.println("Id : "+student.getId());
			System.out.println("Ogrenci Ismi : "+student.getFirstName());
			System.out.println("Ogrenci Soyadi : "+student.getLastName());
			System.out.println("Ogrenci Yasi : " +student.getAge());
			System.out.println("Ogrenci No : " +student.getNo());
			System.out.println("--------------");
		}
	}
}