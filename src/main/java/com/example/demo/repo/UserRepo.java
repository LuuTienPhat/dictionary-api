package com.example.demo.repo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
	List<User> findAllByCreatedAtBetween(LocalDate createdAtStart, LocalDate LocalDate);
}
