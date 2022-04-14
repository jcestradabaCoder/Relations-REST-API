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

import com.jc.library.entity.Book;
import com.jc.library.entity.Library;
import com.jc.library.repository.BookRepository;
import com.jc.library.repository.LibraryRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		Optional<Library> libraryOptional = libraryRepository.findById(book.getLibrary().getId());
		
		if(libraryOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		book.setLibrary(libraryOptional.get());
		Book newBook = bookRepository.save(book);
		
		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newBook.getId()).toUri();
		
		return ResponseEntity.created(ubicacion).body(newBook);
	}
	
	@GetMapping("/books")
	public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
		return ResponseEntity.ok(bookRepository.findAll(pageable));
	}
		
	@GetMapping("/books/{bookId}")
	public ResponseEntity<Book> obtenerLibroPorId(@PathVariable(name = "bookId") Integer bookId) {
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		
		if(bookOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(bookOptional.get());
	}
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable(name = "bookId") Integer bookId, @Valid @RequestBody Book book) {
		Optional<Library> libraryOptional = libraryRepository.findById(book.getLibrary().getId());
		if(libraryOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		if(bookOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		book.setLibrary(libraryOptional.get());
		book.setId(bookOptional.get().getId());
		bookRepository.save(book);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Book> deleteBook(@PathVariable(name = "bookId") Integer bookId) {
		Optional<Book> bookOptional = bookRepository.findById(bookId);
		
		if(bookOptional.isPresent() == false) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		bookRepository.delete(bookOptional.get());
		return ResponseEntity.noContent().build();
	}
}