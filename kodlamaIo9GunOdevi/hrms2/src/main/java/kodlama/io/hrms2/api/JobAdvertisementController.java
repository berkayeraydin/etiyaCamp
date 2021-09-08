package kodlama.io.hrms2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import kodlama.io.hrms2.business.abstracts.JobAdvertisementService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.JobAdvertisement;

@RestController
@RequestMapping("/api/jobadvertisement")
public class JobAdvertisementController {
	
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		
		return this.jobAdvertisementService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@GetMapping("/getByEmployerId")
	public DataResult<List<JobAdvertisement>> getByEmployerId(@RequestParam("id") int id){
		return this.jobAdvertisementService.getByEmployerId(id);
	}
	

	@GetMapping("/getByActiveStatus")
	public DataResult<List<JobAdvertisement>> getByActiveStatus(){
		
	   return this.jobAdvertisementService.getByActiveStatus();
	}
	
	@GetMapping("/getByActiveAndEmployerId")
	public DataResult<List<JobAdvertisement>> getByActiveAndEmployerId(@RequestParam("id") int id){
		
	   return this.jobAdvertisementService.getByActiveAndEmployerId(id);
	}
	
	@GetMapping("/getByAscDate")
	public DataResult<List<JobAdvertisement>> getByAscDate(){
		
		return this.jobAdvertisementService.getByAscDate();
	}
	
	@GetMapping("/getByCompanyNameAndActive")
	public DataResult<List<JobAdvertisement>> getByCompanyNameAndActive(@RequestParam String companyName){
		
		return this.jobAdvertisementService.getByCompanyNameAndActive(companyName);
	}
	
	@GetMapping("/getByCompanyName")
	public DataResult<List<JobAdvertisement>> getByCompanyName(@RequestParam String companyName){
		
		return this.jobAdvertisementService.getByCompanyName(companyName);
	}
	
}
