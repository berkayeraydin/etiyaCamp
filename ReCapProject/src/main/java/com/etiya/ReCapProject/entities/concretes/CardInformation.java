package com.etiya.ReCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cars" })
@Table(name = "cards_information")
public class CardInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_information_id")
	private int cardInformationId;
	
	@Column(name = "card_name")
	private String cardName;

	@Column(name = "card_number")
	private String cardNumber;
	
	@Column(name = "expirationDate")
	private String expirationDate;
	
	@Column(name = "cvv")
	private String cvv;
	
	@ManyToOne()
	@JoinColumn(name="user_id")
	private ApplicationUser applicationUser; 
	

}
