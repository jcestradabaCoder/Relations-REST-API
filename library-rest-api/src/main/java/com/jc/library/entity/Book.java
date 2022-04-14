package com.jc.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "tb_book", 
	uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;
	
	@NotNull
	@Column(name = "title", nullable = false)
	private String title;
	
	
	//LAZY -> Solo trae un dato cuando se lo indiquemos pero no cada vez que consultemos
	//EAGER -> Cada vez que realiza una consulta trae todo
	//JsonProperty -> Ignore la propiedad que se refiere a serializar la cadena, si se inicializa en un contexto no transaccional
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "library_id")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Library library;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", library=" + library + "]";
	}
}