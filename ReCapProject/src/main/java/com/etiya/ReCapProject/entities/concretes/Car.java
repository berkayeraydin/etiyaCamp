package com.etiya.ReCapProject.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","rentals"})
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int carId;
	
	@Column(name = "car_name")
	private String carName;
	
	//@Column(name ="brand_id")
	//private int brandId; 
	
	//@Column(name ="color_id")
	//private int colorId; 
	
	@Column(name ="model_year")
	private String modelYear; 
	
	@Column(name ="daily_price")
	private double dailyPrice; 
	
	@Column(name ="descripton")
	private String descripton; 
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@JsonIgnore
	// ayni arac birden fazla kiralanabilir
	@OneToMany(mappedBy="car")
	private List<Rental> rental;
	
	
	@OneToMany(mappedBy= "car")
	private List<CarImage> carImages;
	
	
	
}
