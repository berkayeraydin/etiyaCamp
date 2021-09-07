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
@Table(name="job_seekers")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","experiences"})
public class JobSeekers {
	
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id")
   private int id;  
   
   @Column(name="first_name")
   private String first_name;
   
   @Column(name="last_name")
   private String last_name;
   
   @Column(name="national_identity")
   private String national_identity;
   
   @Column(name="birth_date")
   private String birth_date;

   
   @OneToOne()
   @JoinColumn(name="user_id")
   private User user;

   
   
  
}
