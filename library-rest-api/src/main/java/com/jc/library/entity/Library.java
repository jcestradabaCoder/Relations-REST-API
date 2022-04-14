package com.jc.library.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_library")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "library_id")
	private int id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	
	@OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
	private Set<Book> books = new HashSet<>();
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
		books.forEach(book -> book.setLibrary(this));
	}

	@Override
	public String toString() {
		return "Library [id=" + id + ", name=" + name + ", books=" + books + "]";
	}
}