package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.example.demo.entities.Meaning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedEnWord {
	private Long id;

	private String word;

	private int views;

	private String pronunciation;

	private List<Meaning> meanings;
}
