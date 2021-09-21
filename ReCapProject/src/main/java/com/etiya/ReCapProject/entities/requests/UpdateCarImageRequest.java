package com.etiya.ReCapProject.entities.requests;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImageRequest {
	
	@NotNull
	private int carImageId;
	
	@NotNull
	private int carId;
	
	@JsonIgnore
	private MultipartFile file;
}

