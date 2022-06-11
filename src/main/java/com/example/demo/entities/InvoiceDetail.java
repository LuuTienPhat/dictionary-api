package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "invoice_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private float price;
}
