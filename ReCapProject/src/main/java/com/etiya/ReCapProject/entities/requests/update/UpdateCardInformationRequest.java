package com.etiya.ReCapProject.entities.requests.update;

import javax.validation.constraints.Size;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardInformationRequest {
	
	
	@NotNull
	private int cardInformationId;
	
	@NotNull
	@Size(max = 25)
	private String cardName;

	@NotNull
	@Size(min = 16, max = 16 , message = "16 haneli Olmalidir.")
	private String cardNumber;
	
	@NotNull
	@Size(min = 5, max = 5 , message = "5 haneli Olmalidir.")
	private String expirationDate;
	
	@NotNull
	@Size(min = 3, max = 3 , message = "3 haneli Olmalidir.")
	private String cvv;
	
}

