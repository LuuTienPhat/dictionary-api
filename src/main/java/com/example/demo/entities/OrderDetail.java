package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders_detail")
@Getter
@Setter
@RequiredArgsConstructor
public class OrderDetail {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// @EmbeddedId
//	private OrderDetailKey id;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	public Long getProductId() {
		return this.product == null ? null : this.product.getId();
	}
	
	public Float getProductPrice() {
		return this.product == null ? null : this.product.getPrice();	}

	public Long getOrderId() {
		return this.order == null ? null : this.order.getId();
	}

//	@Embeddable
//	public static class OrderDetailKey implements Serializable {
//		private static final long serialVersionUID = 1L;
//		
//		@Column(name = "order_id")
//		private String orderId;
//		
//		@Column(name = "product_id")
//	    private String productId;
//		
//	}
}
