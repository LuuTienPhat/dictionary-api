package com.example.demo.entities;

import javax.persistence.*;




@Entity
@Table(name = "synonym_pair")
public class SynonymPair {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "word_id1")
	private EnWord word1;

	@ManyToOne
	@JoinColumn(name = "word_id2")
	private EnWord word2;
}
