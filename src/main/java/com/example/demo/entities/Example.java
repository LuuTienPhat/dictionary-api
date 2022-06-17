package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "example")
@Getter
@Setter
@RequiredArgsConstructor
public class Example {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@JsonSetter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meaning_id")
	private Meaning meaning;

	@Column(name = "example")
	private String example;

	@Column(name = "example_meaning")
	private String exampleMeaning;

	@JsonGetter
	private Long getMeaningId() {
		return this.meaning == null ? null : this.meaning.getId();
	}

}
