package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entities.Order;

public interface OrderService {
	Order insertOrder(Order order);

	Order updateOrder(Long id, Order newOrder);

	void deleteOrder(Long id);

	Order getOrder(Long id);
	
	List<Order> getOrders();
	
	List<Order> getOrders(int offset, int limit);
	
	List<Order> getOrders(String keyword);
	
	List<Order> getOrders(int state);
	
	List<Order> getOrders(LocalDateTime orderDateStart, LocalDateTime orderDateEnd, int state);
	
	List<Order> getOrdersOrderByOrderDateAsc(int state);
	
	Long count();
	
	Long countByOrderDateBetween(LocalDateTime orderDateStart, LocalDateTime orderDateEnd);
}
