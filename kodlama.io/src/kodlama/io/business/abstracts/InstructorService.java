package kodlama.io.business.abstracts;

import java.util.List;

import kodlama.io.entities.concretes.Instructor;

public interface InstructorService {
	
	List<Instructor> getAll();
	void add(Instructor instructor);
}
