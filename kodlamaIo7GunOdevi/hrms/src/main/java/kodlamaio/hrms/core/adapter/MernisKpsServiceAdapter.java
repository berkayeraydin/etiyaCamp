package kodlamaio.hrms.core.adapter;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.mernis.MernisKpsService;

@Service
public class MernisKpsServiceAdapter implements UserIdentityValidatorService{

	@Override
	public boolean isValid(JobSeeker jobSeeker) {
		MernisKpsService mernisKpsService = new MernisKpsService();
		return mernisKpsService.tcKimlikDogrula(Long.parseLong(jobSeeker.getIdentitiyNumber()), jobSeeker.getFirstName(), jobSeeker.getLastName(), jobSeeker.getBirthDate());
	}

}
