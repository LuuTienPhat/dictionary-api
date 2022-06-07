package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "antonym_pair")
public class AntonymPair {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "word_id1")
    private EnWord word1;
	
	@ManyToOne
	@JoinColumn(name = "word_id2")
    private EnWord word2;
}
