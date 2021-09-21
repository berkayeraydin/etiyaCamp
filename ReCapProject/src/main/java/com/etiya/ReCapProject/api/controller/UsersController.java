package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.core.results.DataResult;
import com.etiya.ReCapProject.core.results.Result;
import com.etiya.ReCapProject.entities.concretes.ApplicationUser;
import com.etiya.ReCapProject.entities.requests.CreateApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.DeleteApplicationUserRequest;
import com.etiya.ReCapProject.entities.requests.UpdateApplicationUserRequest;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<ApplicationUser>> getAll(){
		return this.userService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateApplicationUserRequest createApplicationUserRequest) {
		return this.userService.add(createApplicationUserRequest);
	}
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody  UpdateApplicationUserRequest updateApplicationUserRequest) {
		return this.userService.update(updateApplicationUserRequest);
	}
	
	@PostMapping("/delete")
	public Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest) {
		return this.userService.delete(deleteApplicationUserRequest);
	}
}
