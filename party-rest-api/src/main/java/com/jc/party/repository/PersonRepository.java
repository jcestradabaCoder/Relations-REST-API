package com.jc.party.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jc.party.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	Collection<Person> findAll();
}