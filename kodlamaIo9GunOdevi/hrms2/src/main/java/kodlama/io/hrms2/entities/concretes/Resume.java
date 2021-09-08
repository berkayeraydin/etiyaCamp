package kodlama.io.hrms2.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "image")
	private String image;

	@Column(name = "github_link")
	private String githubLink;

	@Column(name = "linkedin_link")
	private String linkedinLink;
	
	
	@OneToOne()
	@JoinColumn(name="jobseeker_id")
    private JobSeekers jobseekers;
	
	@OneToOne()
	   @JoinColumn(name="education_id")
	   private Education education;
	   
	   @OneToOne()
	   @JoinColumn(name="languagejobseeker_id")
	   private LanguageJobseeker languageJobseeker;
	
	
   
	
	
}
