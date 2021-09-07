package kodlama.io.hrms2.core.adapter;

import org.springframework.stereotype.Service;

import kodlama.io.hrms2.entities.concretes.JobSeekers;
import kodlama.io.hrms2.mernis.MernisKpsService;

@Service
public class MernisKpsServiceAdapter implements UserIdentityValidatorService {

	@Override
	public boolean isValid(JobSeekers jobSeekers) {
		MernisKpsService mernisKpsService = new MernisKpsService();
		return mernisKpsService.tcKimlikDogrula(Long.parseLong(jobSeekers.getNational_identity()), jobSeekers.getFirst_name()
				, jobSeekers.getLast_name(), jobSeekers.getBirth_date());
	}

}
