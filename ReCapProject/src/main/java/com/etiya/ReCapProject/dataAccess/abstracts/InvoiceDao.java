package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer>{
	
	Invoice getTop1ByOrderByCreationDateDesc();

	boolean existsByRental_RentalId(int rentalId);

	List<Invoice> getByRental_ApplicationUser_UserId(int userId);

	// List<Invoice> getByCreationDateBeafore(Date maxDate);

	// List<Invoice> getByCreationDateAfter(Date minDate);

	List<Invoice> getByCreationDateBetween(Date minDate, Date maxDate);
	
	Invoice getByRental_RentalId(int rentalId);
}
