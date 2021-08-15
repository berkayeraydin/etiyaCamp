package kodlamaIo5GunOdev.business.concretes;

import kodlamaIo5GunOdev.business.abstracts.CustomerCheckService;
import kodlamaIo5GunOdev.business.abstracts.CustomerService;
import kodlamaIo5GunOdev.business.abstracts.VerificationService;
import kodlamaIo5GunOdev.dateAccess.abstracts.CustomerDao;
import kodlamaIo5GunOdev.entities.concretes.Customer;

public class CustomerManager implements CustomerService {

	CustomerCheckService customerCheckService;
	VerificationService verificationService;
	CustomerDao customerDao;
	
	public CustomerManager(CustomerCheckService customerCheckService, VerificationService verificationService,CustomerDao customerDao) {
		this.customerCheckService = customerCheckService;
		this.verificationService = verificationService;
		this.customerDao = customerDao;
	}
	
	@Override
	//KAYIT OLMA
	public void signUp(Customer customer) {
		//CustomerCheckManager uzerinde cagrilan fonsksiyon islemler sonucu true ise ekliyor
		//Bu bilgilerin tümünün doğru olması duurmunda email doğrulama mail'i gönderiliyor ve database ekleniyor.
		if (customerCheckService.approval(customer) == true) {
			System.out.println(customer.getFirstName() + " kullanicisi sisteme eklendi.");
			//DOGRULAMA MESAJI
			verificationService.sendToVerifyEmail(customer.getEmail());
			//DB EKLENDI
			customerDao.add(customer);
		}
		System.out.println("------------------");

	}
		
	// GIRIS YAPMA
	@Override
	public void login(Customer customer) {
		verificationService.verifyEmail(customer.getEmail());
		//CUSTOMERDAO DAKI LISTTEde EMAIL SIFRE 
		if (customerDao.getEmail(customer.getEmail()) && customerDao.getPassword(customer.getPassword())
				// DOGRULANMISMI DIYE KONTROL EDIYOR
				&& verificationService.checkVerifyAccount(customer.getEmail()) == true) {
			System.out.println("Basariyla giris yaptiniz.");
			
		} else {
			System.out.println("Girilen bilgiler yanlıs.");
		}
		
	}
	
	

}
