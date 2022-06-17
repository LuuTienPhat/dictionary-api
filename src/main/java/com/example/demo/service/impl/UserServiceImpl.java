package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.entities.Category;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{

	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
//	private final UserRoleRepository userRoleRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} to the database", user.getFirstname());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {} to", username);
		return userRepo.findByUsername(username);
	}

	public Role getRole(String roleName) {
		return roleRepo.findByName(roleName);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			log.info("User found in the database: {}", username);
		}

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(),
				authorities);
	}

	@Override
	public void deleteRoleFromUser(String username, String roleName) {
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().remove(role);
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}

	@Override
	public Long count() {
		return userRepo.count();
	}

	@Override
	public User updateUser(Long id, User newUser) {
		User updatedUser = null;
		try {
			log.info("Updating category '{}' to the database", newUser.getFirstname());
			updatedUser = userRepo.findById(id).map(user -> {
				
				user.setFirstname(newUser.getFirstname());
				user.setLastname(newUser.getLastname());
				user.setPhone(newUser.getPhone());
				user.setEmail(newUser.getEmail());
				user.setBirthday(newUser.getBirthday());
				user.setGender(newUser.getGender());
				user.setAddress(newUser.getAddress());
				user.setRoles(newUser.getRoles());
				
				return userRepo.save(user);
			}).orElseGet(() -> {
				newUser.setId(id);
				return userRepo.save(newUser);
			});
		} catch (Exception e) {
			log.info("Error while updating category '{}' to the database", newUser.getFirstname());
			e.printStackTrace();
		}

		return updatedUser;
	}

	@Override
	public List<User> getUsersAreCustomers() {
		Role customerRole = roleRepo.findByName("ROLE_CUSTOMER");
		List<User> customers = userRepo.findAll();
		customers.removeIf(item -> !item.getRoles().contains(customerRole));
		return customers;
	}
}

