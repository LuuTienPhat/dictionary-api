package com.example.demo.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.User;
import com.example.demo.entities.EnWord;
import com.example.demo.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	List<Order> findAllByState(int state);

	@Transactional
	@Modifying
	@Query("SELECT e from Order e "
			+ "WHERE e.orderDate LIKE %?1% "
			+ "or e.shippedDate LIKE %?1% "
			+ "or e.shipName LIKE %?1% "
			+ "or e.state LIKE %?1% "
			+ "or e.shipAddress LIKE %?1% "
			+ "or e.shipPhone LIKE %?1%")
	List<Order> search(String keyword);
	
	List<Order> findAllByOrderDateBetween(LocalDateTime orderDateStart, LocalDateTime orderDateEnd);
	
	List<Order> findAllByStateOrderByOrderDateAsc(Integer state);
}
