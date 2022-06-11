package com.example.demo.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "en_word_id")
	private EnWord enWord;

	@ManyToOne
	@JoinColumn(name = "part_of_speech_id")
	private PartOfSpeech partOfSpeech;

	@Column(name = "meaning")
	private String meaning;

	@OneToMany(mappedBy = "meaning", fetch = FetchType.LAZY)
	private List<Example> examples;

	public Long getEnWordId() {
		return this.enWord == null ? null : this.enWord.getId();
	}

//	public Long getPartOfSpeechId() {
//		return this.partOfSpeech == null ? null : this.partOfSpeech.getId();
//	}
}
