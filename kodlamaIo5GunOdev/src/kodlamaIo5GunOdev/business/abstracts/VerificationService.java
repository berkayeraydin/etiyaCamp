package kodlamaIo5GunOdev.business.abstracts;

public interface VerificationService {
	
	//mail dogrulama 
	void sendToVerifyEmail(String email);
	//dogrulanmis
	void verifyEmail(String email);
	// hesabi dogrulama kontrol etme
	boolean checkVerifyAccount(String email);

}
