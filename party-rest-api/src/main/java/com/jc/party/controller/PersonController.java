package com.jc.party.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc.party.entity.Person;
import com.jc.party.entity.Party;
import com.jc.party.repository.PersonRepository;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	
	@PostMapping("/people")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
	}
	
	@GetMapping("/people")
	public ResponseEntity<Collection<Person>> getPeople() {
		return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/people/{personId}")
	public ResponseEntity<Person> getPersonById(@PathVariable(name = "personId") Long personId) {
		Person person = personRepository.findById(personId).orElseThrow();
		
		if(person != null) {
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
		else { 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/people/{personId}")
	public ResponseEntity<Person> updatePerson(@PathVariable(name = "personId") Long personId, @RequestBody Person person) {
	
		Person currentPerson = personRepository.findById(personId).orElseThrow();
		
		if(currentPerson != null) {
			person.setId(currentPerson.getId());
			return new ResponseEntity<>(personRepository.save(person), HttpStatus.OK);
		}
		else { 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/people/{personId}")
	public ResponseEntity<?> deletePerson(@PathVariable(name = "personId") Long personId) {
		personRepository.deleteById(personId);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/people/{personId}/parties")
	public ResponseEntity<Collection<Party>> getPersonParties(@PathVariable(name = "personId") Long personId) {
		Person person = personRepository.findById(personId).orElseThrow();
		
		if(person != null) {
			return new ResponseEntity<>(person.getParties(), HttpStatus.OK);
		}
		else { 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
}