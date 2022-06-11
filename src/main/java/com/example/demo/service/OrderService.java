package com.example.demo.service;

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
	
	Long count();
}
