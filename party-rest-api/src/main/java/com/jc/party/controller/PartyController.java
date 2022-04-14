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

import com.jc.party.entity.Party;
import com.jc.party.repository.PartyRepository;

@RestController
@RequestMapping("/api")
public class PartyController {

	@Autowired
	private PartyRepository partyRepository;
	
	
	@PostMapping("/parties")
	public ResponseEntity<Party> createParty(@RequestBody Party party) {
		return new ResponseEntity<>(partyRepository.save(party), HttpStatus.CREATED);
	}
	
	@GetMapping("/parties")
	public ResponseEntity<Collection<Party>> getAllParties() {
		return new ResponseEntity<>(partyRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/parties/{partyId}")
	public ResponseEntity<Party> getPartyById(@PathVariable(name = "partyId") Long partyId) {
		Party party = partyRepository.findById(partyId).orElseThrow();
		
		if(party != null) {
			return new ResponseEntity<>(party, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/parties/{partyId}")
	public ResponseEntity<Party> updateParty(@RequestBody Party party, @PathVariable(name = "partyId") Long partyId) {
		Party currentParty = partyRepository.findById(partyId).orElseThrow();
		
		if(currentParty != null) {
			party.setId(currentParty.getId());
			return new ResponseEntity<>(partyRepository.save(party),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/parties/{partyId}")
	public ResponseEntity<?> deleteParty(@PathVariable(name = "partyId") Long partyId) {
		partyRepository.deleteById(partyId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}