package com.etiya.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.etiya.ReCapProject.core.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "corporateCustomer" })
@Table(name = "users")
public class ApplicationUser extends User {

	@OneToOne(mappedBy = "applicationUser")
	private IndividualCustomer individualCustomer;

	@OneToOne(mappedBy = "applicationUser")
	private CorporateCustomer corporateCustomer;

	@JsonIgnore
	@OneToMany(mappedBy = "applicationUser")
	private List<Rental> rentals;

	@JsonIgnore
	@OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
	private List<CardInformation> cardInformations;

}