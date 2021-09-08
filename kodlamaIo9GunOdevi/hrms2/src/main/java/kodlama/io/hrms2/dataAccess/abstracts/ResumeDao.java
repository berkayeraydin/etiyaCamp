package kodlama.io.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms2.entities.concretes.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer>{
	
	Resume getByJobseekers_id(int id);
}
