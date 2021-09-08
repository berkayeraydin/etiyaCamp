package kodlama.io.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms2.entities.concretes.LanguageJobseeker;

public interface LanguageJobseekerDao extends JpaRepository<LanguageJobseeker,Integer>{

}
