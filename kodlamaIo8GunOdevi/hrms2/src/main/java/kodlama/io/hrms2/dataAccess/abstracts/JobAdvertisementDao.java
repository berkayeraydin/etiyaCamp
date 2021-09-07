package kodlama.io.hrms2.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlama.io.hrms2.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {
	
	// Employer id sine gore getirme.
	List<JobAdvertisement> getByEmployer_Id(int employer_id);
	
	// Aktif is ilanlari listelendi.
	@Query("From JobAdvertisement where is_active = true")
	List<JobAdvertisement> getByActiveStatuss();
	
	// Is verenin id sine gore, aktif is ilanlarinin listelenmesi 
	@Query("From JobAdvertisement where is_active=true and employer.id=:employerId")
	List<JobAdvertisement> getByActiveAndEmployerId(int employerId);
	
	// tum aktif is ilanlari tarihe gore kucukten buyuge dogru listelendi.
	@Query("From JobAdvertisement where is_active=true ORDER BY due_date ASC")
    List<JobAdvertisement> getByAscDate();
	
	// Is verenin ismine gore, aktif is ilanlarinin listelenmesi   and is_active = true
	@Query("From JobAdvertisement where employer.companyName=:companyName and is_active = true")
	List<JobAdvertisement> getByCompanyNameAndActive(String companyName);
	
	// is verenin companyName ine gore listeleme
	@Query("From JobAdvertisement where employer.companyName=:companyName")
	List<JobAdvertisement> getByCompanyName(String companyName);
	
	
}
