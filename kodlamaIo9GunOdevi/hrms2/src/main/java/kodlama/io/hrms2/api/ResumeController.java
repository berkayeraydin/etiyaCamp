package kodlama.io.hrms2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms2.business.abstracts.ResumeService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.Resume;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
	
	private ResumeService resumeService;
	
	@Autowired
	public ResumeController(ResumeService resumeService) {
		super();
		this.resumeService = resumeService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Resume>> getAll(){
		
		return this.resumeService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Resume> getById(@RequestParam("id") int id){
		
		return this.resumeService.getById(id);
	}
	
	@PostMapping("/add")
	public Result add(@io.swagger.v3.oas.annotations.parameters.RequestBody Resume resume) {
		
		return this.resumeService.add(resume);
	}
}
