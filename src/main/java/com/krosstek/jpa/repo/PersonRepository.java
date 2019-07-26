package com.krosstek.jpa.repo;



import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.krosstek.jpa.model.Person;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	//*This config for MySQL*
	//@QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))

	@Query(value = "select t from Person t") 
	Stream<Person> streamAll();

}
