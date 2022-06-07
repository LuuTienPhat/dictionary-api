package com.example.demo.entities;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "example")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Example {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@ManyToOne
	@JoinColumn(name = "meaning_id", updatable = false, insertable = false, referencedColumnName = "id")
	private Meaning meaning;

	@Column(name = "example")
	private String example;

	@Column(name = "example_meaning")
	private String exampleMeaning;

}
