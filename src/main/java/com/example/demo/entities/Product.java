package com.example.demo.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product extends Base {
	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "unit")
	private String unit;

	@Column(name = "price")
	private float price;

	@Column(name = "description", nullable = true)
	private String description;

	@Column(name = "views")
	private Integer views;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//	private List<CartDetailEntity> cartDetails;

//	@JsonIgnore
//	@JsonBackReference
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails;

//	@JsonBackReference
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//	private List<ImageEntity> images;

//	@JsonBackReference
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<InvoiceDetail> invoiceDetails;

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//	private List<FavoriteProductEntity> favoriteProducts;

	// GET TOTAL REVENUE OF SINGLE PRODUCT IN ORDERS
//	public float getGrossRevenueInOrders(String productId) {
//		float revenue = 0;
//		for (OrderDetail orderDetail : orderDetails) {
//			if (orderDetail.getProduct().getId().equals(productId)) {
//				revenue += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
//			}
//		}
//		return revenue;
//	}

	// GET TOTAL REVENUE OF SINGLE PRODUCT IN ORDERS OF THIS MONTH
//	public float getGrossRevenueInOrdersOfThisMonth(String productId) throws ParseException {
//		LocalDate now = LocalDate.now();
//		LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
//		LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
//
//		String lastDayOfMonthString = lastDayOfMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		String firstDayOfMonthString = firstDayOfMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date d1 = formatter.parse(firstDayOfMonthString);
//		Date d2 = formatter.parse(lastDayOfMonthString);
//
//		float revenue = 0;
//		for (OrderDetail orderDetail : orderDetails) {
//			if (orderDetail.getProduct().getId().equals(productId)) {
//				if (orderDetail.getOrder().getOrderDate().after(d1)
//						&& orderDetail.getOrder().getOrderDate().before(d2)) {
//					revenue += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
//				}
//			}
//		}
//		return revenue;
//	}
//
//	public float getBounceRate(String productId) throws ParseException {
//		float revenueThisMonth = getGrossRevenueInOrdersOfThisMonth(productId);
//		float revenueLastMonth = getGrossRevenueInOrdersOfLastMonth(productId);
//		return getBounceRate(revenueThisMonth, revenueLastMonth);
//	}
//
//	public float getBounceRate(float revenueThisMonth, float revenueLastMonth) {
//		if (revenueLastMonth == 0)
//			return 0;
//		else
//			return ((revenueLastMonth - revenueThisMonth) / revenueLastMonth) * 100;
//	}
//
//	public float getGrossRevenueInOrdersOfLastMonth(String productId) throws ParseException {
//		LocalDate now = LocalDate.now().minusMonths(1);
//		LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
//		LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
//
//		String lastDayOfMonthString = lastDayOfMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		String firstDayOfMonthString = firstDayOfMonth.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date d1 = formatter.parse(firstDayOfMonthString);
//		Date d2 = formatter.parse(lastDayOfMonthString);
//
//		float revenue = 0;
//		for (OrderDetail orderDetail : orderDetails) {
//			if (orderDetail.getProduct().getId().equals(productId)) {
//				if (orderDetail.getOrder().getOrderDate().after(d1)
//						&& orderDetail.getOrder().getOrderDate().before(d2)) {
//					revenue += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
//				}
//			}
//		}
//		return revenue;
//	}
//
//	public float getTotalRevenueInOrders() throws ParseException {
//		float revenue = 0;
//		for (OrderDetail orderDetail : this.orderDetails) {
//			revenue += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
//		}
//		return revenue;
//	}

//	public float getTotalRevenueInOrders(LocalDate beginDate, LocalDate endDate) throws ParseException {
//		float revenue = 0;
//		for (OrderDetail orderDetail : this.orderDetails) {
//			LocalDate orderDate = DateTimeValidator.convertToLocalDateViaInstant(orderDetail.getOrder().getOrderDate());
//			if (orderDate.isAfter(beginDate) && orderDate.isBefore(endDate))
//				revenue += orderDetail.getQuantity() * orderDetail.getProduct().getPrice();
//		}
//		return revenue;
//	}
}
