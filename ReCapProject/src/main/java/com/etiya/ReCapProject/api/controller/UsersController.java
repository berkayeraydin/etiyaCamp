package com.etiya.ReCapProject.api.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.requests.update.UpdateApplicationUserRequest;

@RestController
@RequestMapping("api/users")
public class UsersController {
	private UserService userService;

	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
//
//	@GetMapping("/getAll")
//	public DataResult<List<ApplicationUser>> getAll() {
//		return this.userService.getAll();
//	}

	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateApplicationUserRequest updateApplicationUserRequest) {
		return this.userService.update(updateApplicationUserRequest);
	}

}
