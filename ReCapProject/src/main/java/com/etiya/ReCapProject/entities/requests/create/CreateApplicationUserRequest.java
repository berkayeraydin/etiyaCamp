package com.etiya.ReCapProject.entities.requests.create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationUserRequest {
	
	@NotBlank
	@NotNull
	private String password;
	
	@Email
	@NotNull
	private String email;
	
}
