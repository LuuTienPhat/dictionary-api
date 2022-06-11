package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name = "invoice_type")
public class InvoiceTypeEntity {
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, insertable = false)
	private int id;

	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "invoiceType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Invoice> invoices;
}
