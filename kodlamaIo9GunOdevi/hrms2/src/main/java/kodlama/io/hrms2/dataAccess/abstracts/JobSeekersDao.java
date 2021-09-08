package kodlama.io.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms2.entities.concretes.JobSeekers;

public interface JobSeekersDao  extends JpaRepository<JobSeekers,Integer>{

}
