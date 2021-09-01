package kodlama.io.dataAccess.abstracts;

import java.util.List;

import kodlama.io.entities.concretes.Course;

public interface CourseDao extends EntityRepository<Course>{

	List<Course> selectedFilterByCategory(int categoryId);
	
	
	
}
