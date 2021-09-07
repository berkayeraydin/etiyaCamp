package kodlama.io.hrms2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import kodlama.io.hrms2.business.abstracts.JobSeekerService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.JobSeekers;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {
	
	private JobSeekerService jobSeekerService;
	
	@Autowired
	public JobSeekerController(JobSeekerService jobSeekerService) {
		super();
		this.jobSeekerService = jobSeekerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobSeekers>> getAll(){
		return this.jobSeekerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobSeekers jobSeekers) {
		return this.jobSeekerService.add(jobSeekers);
	}

}
