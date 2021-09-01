package kodlama.io.dataAccess.concretes.hibernate;

import java.util.ArrayList;
import java.util.List;

import kodlama.io.dataAccess.abstracts.InstructorDao;
import kodlama.io.entities.concretes.Instructor;

public class HibernateInstructorDao implements InstructorDao{
	
	List<Instructor> instructors = new ArrayList<Instructor>();
	
	
	@Override
	public List<Instructor> getAll() {
		return this.instructors;
	}


	@Override
	public void add(Instructor entity) {
		this.instructors.add(entity);
		
	}


	@Override
	public void update(Instructor entity) {
		
		System.out.println("HibernateInstructorDao ile Guncellendi");
		
	}


	@Override
	public void remova(Instructor entity) {
		this.instructors.remove(entity);
	}

}
