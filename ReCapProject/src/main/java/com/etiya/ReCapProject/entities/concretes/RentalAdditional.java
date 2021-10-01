package com.etiya.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rentals"})
@Table(name = "rental_additionals")
public class RentalAdditional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_additional_id")
	private int rentalAdditionalId;

	@Column(name = "rental_additional_name")
	private String rentalAdditionalName;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "rentalAdditionals")
	private List<Rental> rentals;


}
