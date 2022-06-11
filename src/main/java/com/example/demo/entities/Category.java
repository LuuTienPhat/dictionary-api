package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.domain.Base;
import com.example.demo.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Base {
	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
//	@Column(name = "image")
//	private String image;
//	@Temporal(TemporalType.DATE)
//	@Column(name = "date_added", updatable = false)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	private Date dateAdded;
	@Column(name = "description")
	private String description;

//	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true)
//	private List<Product> products = new ArrayList<Product>();
}
