package com.example.demo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.example.demo.entities.EnWord;
import com.example.demo.entities.Feedback;
import com.example.demo.entities.SavedWord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String username;
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Role> roles = new ArrayList<>();

//	@ManyToMany(fetch = FetchType.LAZY)
//	private Collection<SavedWord> enWords = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private Collection<SavedWord> enWords = new ArrayList<>();

	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<Feedback> feedbacks = new ArrayList<>();
}
