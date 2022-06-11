package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
	@Id
	@Column(name = "id", updatable = false)
	private String id;
	@Column(name = "date")
	/* @Temporal(TemporalType.DATE) */
	private String date;
	@Column(name = "time")
	/* @Temporal(TemporalType.TIMESTAMP) */
	private String time;

//	@ManyToOne()
//	@JoinColumn(name = "admin_id")
//	private AdminEntity admin;

	@ManyToOne()
	@JoinColumn(name = "invoice_type_id")
	private InvoiceTypeEntity invoiceType;

	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<InvoiceDetail> invoiceDetails;

	public boolean hasProduct(String productId) {
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			if (invoiceDetail.getProduct().getId().equals(productId))
				return true;
		}
		return false;
	}

	// GET TOTAL QUANTITY OF PRODUCT IN INVOICE
	public int getTotalQuantity() {
		int totalQuantity = 0;
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			totalQuantity += invoiceDetail.getQuantity();
		}
		return totalQuantity;
	}

	// GET TOTAL PRICE OF INVOICE
	public float getTotalPrice() {
		float totalPrice = 0;
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			totalPrice += (invoiceDetail.getPrice() * invoiceDetail.getQuantity());
		}
		return totalPrice;
	}

	public int getQuantity(String productId) {
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			if (invoiceDetail.getProduct().getId().equals(productId))
				return invoiceDetail.getQuantity();
		}
		return 0;
	}

	public float getPrice(String productId) {
		for (InvoiceDetail invoiceDetail : invoiceDetails) {
			if (invoiceDetail.getProduct().getId().equals(productId))
				return invoiceDetail.getPrice();
		}
		return 0;
	}

}
