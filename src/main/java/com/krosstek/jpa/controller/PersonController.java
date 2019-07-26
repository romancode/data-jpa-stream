package com.krosstek.jpa.controller;

import java.io.IOException;

import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krosstek.jpa.engine.Producer;
import com.krosstek.jpa.model.Person;

import com.krosstek.jpa.repo.PersonRepository;


@RestController
@RequestMapping("/api/person")
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	private Producer producer;
	

	@GetMapping("/hello/")
	public ResponseEntity<?>  getMessage() {
		return new ResponseEntity<String>("Hello!", HttpStatus.OK);
	}


	@GetMapping("/streamall/")
	@Transactional
	public void getAllData() throws IOException {
		long startTime = System.nanoTime();
		try(Stream<Person> dataStream = personRepository.streamAll()) {

			dataStream.forEach(person -> {

				//System.out.println(person.toString()+ "\n");
				producer.sendMessage(person.toString());

			});

			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.


			System.out.println("Time: " + duration + " milliseconds");
		}

	}
	@GetMapping("/streamallasync/")
	@Transactional
	@Async
	public void getAllDataAsync() throws IOException {
		long startTime = System.nanoTime();
		try(Stream<Person> dataStream = personRepository.streamAll()) {

			dataStream.forEach(person -> {

				producer.sendMessage(person.toString());

			});

			long endTime = System.nanoTime();
			long duration = (endTime - startTime)/1000000;


			System.out.println("Time: " + duration + " milliseconds");
		}

	}

}
