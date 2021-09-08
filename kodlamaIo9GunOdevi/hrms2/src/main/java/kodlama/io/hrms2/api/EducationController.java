package kodlama.io.hrms2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms2.business.abstracts.EducationService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.Education;

@RestController
@RequestMapping("/api/educations")
public class EducationController {
	
	private EducationService educationService;
	
	@Autowired
	public EducationController(EducationService educationService) {
		super();
		this.educationService = educationService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Education>> getAll(){
		
		return this.educationService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Education> getById(@RequestParam("id") int id){
		return this.educationService.getById(id);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Education education) {
		
		return this.educationService.add(education);
	}
	
	

	
	
	
}
