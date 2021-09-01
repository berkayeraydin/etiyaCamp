package kodlama.io.business.concretes;

import java.util.List;

import kodlama.io.business.abstracts.InstructorService;
import kodlama.io.dataAccess.abstracts.InstructorDao;
import kodlama.io.entities.concretes.Instructor;

public class InstructorManager implements InstructorService{
	
	InstructorDao instructorDao;
	
	public InstructorManager(InstructorDao instructorDao) {
		super();
		this.instructorDao = instructorDao;
	}

	@Override
	public List<Instructor> getAll() {
		
		return this.instructorDao.getAll();
	}

	@Override
	public void add(Instructor instructor) {
		this.instructorDao.add(instructor);
		
	}

}
