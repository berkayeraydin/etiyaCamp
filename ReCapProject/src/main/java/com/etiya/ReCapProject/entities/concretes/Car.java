package com.etiya.ReCapProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int carId;
	
	@Column(name ="brand_id")
	private int brandId; 
	
	@Column(name ="color_id")
	private int colorId; 
	
	@Column(name ="model_year")
	private String modelYear; 
	
	@Column(name ="daily_price")
	private String dailyPrice; 
	
	@Column(name ="descripton")
	private String descripton; 
	
}
