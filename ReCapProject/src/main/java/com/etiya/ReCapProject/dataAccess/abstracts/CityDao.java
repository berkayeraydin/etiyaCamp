package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer>{

	boolean existsByCityName(String cityName);
	
}
