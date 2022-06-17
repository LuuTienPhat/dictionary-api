package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Example;
import com.example.demo.entities.EnWord;

public interface ExampleService {
	Example insertExample(Example Example);

	Example updateExample(Long id, Example newExample);

	void deleteExample(Long id);

	Example getExample(Long id);

//	Example getExample(String word);
	
	List<Example> getExamples();
	
//	List<Example> getExamples(String keyword);
	
	Long count();
}
