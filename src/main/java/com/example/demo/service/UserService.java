package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;

public interface UserService {
	User saveUser(User user);

	Role saveRole(Role role);
	
	void deleteRoleFromUser(String username, String roleName);

	void addRoleToUser(String username, String roleName);

	User getUser(String username);
	
	User getUser(Long id);

	List<User> getUsers();
}
