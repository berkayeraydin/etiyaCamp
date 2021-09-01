package kodlama.io.business.concretes;

import java.util.List;

import kodlama.io.business.abstracts.CourseService;
import kodlama.io.dataAccess.abstracts.CourseDao;
import kodlama.io.entities.concretes.Course;

public class CourseManager implements CourseService{
	
	CourseDao courseDao;
	
	public CourseManager(CourseDao courseDao) {
		super();
		this.courseDao = courseDao;
	}

	@Override
	public List<Course> getAll() {
		return this.courseDao.getAll();
	}

	@Override
	public void add(Course course) {
		this.courseDao.add(course);
	}

	@Override
	public void search(String string) {
		for (Course courseIndex : this.courseDao.getAll()) {
			if(courseIndex.getName() == string || courseIndex.getInstructor().getFirstName() == string) {
				System.out.println(string + " aramasinin sonucu cikan kurslar " + courseIndex.getName());
			}
		}
	
	}

}
