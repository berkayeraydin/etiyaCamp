package com.etiya.ReCapProject.entities.requests.create;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateCarImageRequest {
	
	
	@NotNull
	private int carId;
	
	@JsonIgnore
	private MultipartFile file;
	
	
}
