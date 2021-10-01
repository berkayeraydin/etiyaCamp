package com.etiya.ReCapProject.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","invoice" })
@Table(name = "rentals")

public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rental_id")
	private int rentalId;

	@Column(name = "rent_date")
	private Date rentDate;

	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "rent_kilometer")
	private long rentKilometer;
	
	@Column(name = "return_kilometer")
	private long returnKilometer;

	@Column(name = "is_car_returned" )
	private boolean isCarReturned = false;
	
	@ManyToOne
	@JoinColumn(name = "take_city_id")
	private City takeCity;
	
	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private ApplicationUser applicationUser;
	
	@OneToOne(mappedBy = "rental")
	private Invoice invoice; 
	
	@ManyToMany()
	@JoinTable(name = "rental_rental_additionals", 
	joinColumns = @JoinColumn(name = "rental_id"), 
	inverseJoinColumns = @JoinColumn(name = "rental_additional_id"))
	private List<RentalAdditional> rentalAdditionals;
	
	
	
}

