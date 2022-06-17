package com.example.demo.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meaning")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meaning {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@JsonSetter
	@ManyToOne
	@JoinColumn(name = "en_word_id")
	private EnWord enWord;

	@ManyToOne
	@JoinColumn(name = "part_of_speech_id")
	private PartOfSpeech partOfSpeech;

	@Column(name = "meaning")
	private String meaning;

	@JsonManagedReference
	@OneToMany(mappedBy = "meaning", fetch = FetchType.LAZY)
	private List<Example> examples;

	public Long getEnWordId() {
		return this.enWord == null ? null : this.enWord.getId();
	}

//	public Long getPartOfSpeechId() {
//		return this.partOfSpeech == null ? null : this.partOfSpeech.getId();
//	}
}
