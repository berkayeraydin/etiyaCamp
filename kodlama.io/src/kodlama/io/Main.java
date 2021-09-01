package kodlama.io;

import kodlama.io.business.abstracts.CategoryService;
import kodlama.io.business.abstracts.CourseService;
import kodlama.io.business.abstracts.InstructorService;
import kodlama.io.business.concretes.CategoryManager;
import kodlama.io.business.concretes.CourseManager;
import kodlama.io.business.concretes.InstructorManager;
import kodlama.io.dataAccess.concretes.hibernate.HibernateCategoryDao;
import kodlama.io.dataAccess.concretes.hibernate.HibernateCourseDao;
import kodlama.io.dataAccess.concretes.hibernate.HibernateInstructorDao;
import kodlama.io.entities.concretes.Category;
import kodlama.io.entities.concretes.Course;
import kodlama.io.entities.concretes.Instructor;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("------ Instructor Transactions -------");
		
		Instructor instructor = new Instructor(1,1,"Engin","DEMIROG","engin@gmail.com","https://www.filepicker.io/api/file/GsqiGFGCRmuBuel0qqF3");
		
		InstructorService instructorService = new InstructorManager(new HibernateInstructorDao());
		instructorService.add(instructor);
		
		for (Instructor instructorIndex : instructorService.getAll()) {
			System.out.println(instructorIndex);
		}
		
		System.out.println();
		System.out.println("------ Course Transactions -------");
		
		Course course1 = new Course(1,1,"Yazılım Geliştirici Yetiştirme Kampı (C# + ANGULAR)"
				,"https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=width:705/https://www.filepicker.io/api/file/Zk7d1MdoSJ6cEShVbfd0",instructor);
		Course course2 = new Course(2,1,"Yazılım Geliştirici Yetiştirme Kampı (JAVA + REACT)"
				,"https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=width:705/https://www.filepicker.io/api/file/qi4s19xSKCmtaaRUqUFI",instructor);
		Course course3 = new Course(3,2,"Programlamaya Giriş için Temel Kurs"
				,"https://process.fs.teachablecdn.com/ADNupMnWyR7kCWRvm76Laz/resize=width:705/https://www.filepicker.io/api/file/BBLmG3XFTtm8XBTrzg4v",instructor);
		
		CourseService courseService = new CourseManager(new HibernateCourseDao());
		courseService.add(course1);
		courseService.add(course2);
		courseService.add(course3);
		
		for (Course courseIndex : courseService.getAll()) {
			System.out.println(courseIndex);
		}
		System.out.println();
		System.out.println("------ Isme Gore Arama -------");
		courseService.search("Engin");
		System.out.println("---------------");
		courseService.search("JAVA");
		
		
		System.out.println();
		System.out.println("------ Category Transactions -------");
		
		Category category1 = new Category(1,"Programlama",course1);
		Category category2 = new Category(1,"Programlama",course2);
		Category category3 = new Category(2,"Programlama",course3);
		
		CategoryService categoryService = new CategoryManager(new HibernateCategoryDao());
		categoryService.add(category1);
		categoryService.add(category2);
		categoryService.add(category3);
		
		for (Category categoryIndex : categoryService.getAll()) {
			System.out.println(categoryIndex);
		}
		System.out.println("-------- Uzun Hali -------");
		System.out.print(categoryService.getAll());

	}

}
