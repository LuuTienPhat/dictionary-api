package com.example.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.Feedback;
import com.example.demo.entities.SavedWord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends Base{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "lastname")
	private String lastname;

	@Column(name = "firstname")
	private String firstname;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="birthday")
	private LocalDate birthday;
	
	@Column(name="gender")
	private Integer gender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="address")
	private String address;

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Role> roles = new ArrayList<>();

//	@ManyToMany(fetch = FetchType.LAZY)
//	private Collection<SavedWord> enWords = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private Collection<SavedWord> enWords = new ArrayList<>();

	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Feedback> feedbacks = new ArrayList<>();

}
