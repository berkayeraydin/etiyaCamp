package kodlama.io.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.dataAccess.abstracts.CourseDao;
import kodlama.io.entities.concretes.Course;

public class HibernateCourseDao implements CourseDao {
	
	List<Course> courses = new ArrayList<Course>();
	
	@Override
	public List<Course> getAll() {
		return this.courses;
	}

	@Override
	public void add(Course entity) {
		this.courses.add(entity);
	}

	@Override
	public void update(Course entity) {
		
		int index = this.courses.indexOf(entity);
		Course course = this.courses.get(index);
		
		course.setId(entity.getId());
		course.setCategoryId(entity.getCategoryId());
		course.setName(entity.getName());
		course.setImg(entity.getImg());
		course.setInstructor(entity.getInstructor());
	}

	@Override
	public void remova(Course entity) {
		this.courses.remove(entity);
	}

	@Override
	public List<Course> selectedFilterByCategory(int categoryId) {
		List<Course> matchedCourseds = new ArrayList<Course>();
		for (Course course : this.courses) {
			if(course.getCategoryId() == categoryId) {
				matchedCourseds.add(course);
			}
		}
		return matchedCourseds;
	}

}
