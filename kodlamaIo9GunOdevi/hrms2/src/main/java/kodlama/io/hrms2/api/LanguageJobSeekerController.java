package kodlama.io.hrms2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms2.business.abstracts.LanguageJobSeekerService;
import kodlama.io.hrms2.core.utilities.results.DataResult;
import kodlama.io.hrms2.core.utilities.results.Result;
import kodlama.io.hrms2.entities.concretes.LanguageJobseeker;

@RestController
@RequestMapping("/api/Languagejobseeker")
public class LanguageJobSeekerController {
	
	private LanguageJobSeekerService languageJobSeekerService;
	
	@Autowired
	public LanguageJobSeekerController(LanguageJobSeekerService languageJobSeekerService) {
		super();
		this.languageJobSeekerService = languageJobSeekerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<LanguageJobseeker>> getAll(){
		
		return this.languageJobSeekerService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody LanguageJobseeker languageJobseeker) {
		
		return this.languageJobSeekerService.add(languageJobseeker);
	}
}
