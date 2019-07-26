package com.krosstek.jpa;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.krosstek.jpa.model.Person;
import com.krosstek.jpa.repo.PersonRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public void run(String... args) throws Exception {
		this.InsertBulkData();

	}

	public void InsertBulkData() {

		for(int i=1;i<=1000000;i++) {
			
			Person p1 = new Person();
			p1.setName("Name" + i);
			p1.setCountry("Country "+ i);
			p1.setGender("M");

			personRepository.save(p1);
		}
	}

}
