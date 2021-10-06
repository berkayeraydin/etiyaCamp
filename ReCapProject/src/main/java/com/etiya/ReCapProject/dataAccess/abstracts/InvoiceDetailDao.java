package com.etiya.ReCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.ReCapProject.entities.concretes.InvoiceDetail;

public interface InvoiceDetailDao extends JpaRepository<InvoiceDetail, Integer>{

	List<InvoiceDetail> getByInvoice_InvoiceId(int invoiceId);
	
	@Query("SELECT sum(i.totalPrice) from InvoiceDetail i Where i.invoice.invoiceId = :invoiceId")
	double SumTotalPriceByInvoice_InvoiceId(int invoiceId);
	
	boolean existsByInvoice_InvoiceId(int invoiceId);
}
