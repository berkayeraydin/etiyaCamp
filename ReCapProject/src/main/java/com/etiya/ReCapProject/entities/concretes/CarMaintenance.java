package com.etiya.ReCapProject.entities.concretes;

import java.util.Date;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "car_maintenances")
public class CarMaintenance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_maintenance_id")
	private int carMaintenanceId;

	@Column(name = "rent_date")
	private Date maintenanceDate;

	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "description")
	private String description;

	@Column(name = "is_car_returned", columnDefinition = "boolean default false")
	private boolean isCarReturned;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
}
