package kodlama.io.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms2.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer> {
	
	
}
