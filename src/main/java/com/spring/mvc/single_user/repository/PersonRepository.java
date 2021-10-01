package com.spring.mvc.single_user.repository;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;
import com.spring.mvc.single_user.entities.Person;

/*
@Repository
public interface PersonRepository extends org.springframework.data.repository.Repository<Person, Integer> {
	Person findByLastName(String lastName);
}
*/

@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepository {
	Person findByLastName(String lastName);
	
}

