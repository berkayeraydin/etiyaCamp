package com.etiya.ReCapProject.entities.concretes;

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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","invoice"})
@Table(name = "invoice_detail")
public class InvoiceDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_detail_id")
	private int invoiceDetailId;
	
	@Column(name = "invoice_detail_name")
	private String invoiceDetailName;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@ManyToOne()
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;
	
	
}
