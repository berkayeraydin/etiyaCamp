package kodlama.io.hrms2.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms2.entities.concretes.City;

public interface CityDao extends JpaRepository<City,Integer>{

}
