package com.etiya.ReCapProject.entities.requests.update;

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
public class UpdateApplicationUserRequest {
	
	@NotNull
	private int userId;
	
	@NotBlank
	@NotNull
	private String firstName;
	
	@NotBlank
	@NotNull
	private String lastName;
	
	@NotBlank
	@Email
	@NotNull
	private String email;
	
	@NotBlank
	@NotNull
	private String password;
	
	@NotBlank
	@NotNull
	private String passwordConfirm;
}
