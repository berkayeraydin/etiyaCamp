package kodlama.io.business.abstracts;

import java.util.List;

import kodlama.io.entities.concretes.Course;

public interface CourseService {
	
	List<Course> getAll();
	void add(Course course);
	void search(String string);
}
