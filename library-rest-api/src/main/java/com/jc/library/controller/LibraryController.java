package com.jc.library.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jc.library.entity.Library;
import com.jc.library.repository.LibraryRepository;

@RestController
@RequestMapping("/api")
public class LibraryController {

	@Autowired
	private LibraryRepository libraryRepository;
	
	
	@PostMapping("/libraries")
	public ResponseEntity<Library> createLibrary(@Valid @RequestBody Library library) {
		Library newLibrary = libraryRepository.save(library);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newLibrary.getId()).toUri();
		return ResponseEntity.created(location).body(newLibrary);
	}
	
	@GetMapping("/libraries")
	public ResponseEntity<Page<Library>> getAllLibraries(Pageable pageable) {
		return ResponseEntity.ok(libraryRepository.findAll(pageable));
	}
	
	@GetMapping("/libraries/{libraryId}")
	public ResponseEntity<Library> getLibraryById(@PathVariable(name = "libraryId") Integer libraryId) {
		Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
		
		if(libraryOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(libraryOptional.get());
	}
	
	@PutMapping("/libraries/{libraryId}")
	public ResponseEntity<Library> updateLibrary(@PathVariable(name = "libraryId") Integer libraryId, @Valid @RequestBody Library library) {
		Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
		
		if(libraryOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		library.setId(libraryOptional.get().getId());
		libraryRepository.save(library);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/libraries/{libraryId}")
	public ResponseEntity<Library> deleteLibrary(@PathVariable(name = "libraryId") Integer libraryId) {
		Optional<Library> libraryOptional = libraryRepository.findById(libraryId);
		
		if(libraryOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		libraryRepository.delete(libraryOptional.get());
		return ResponseEntity.noContent().build();
	}
}