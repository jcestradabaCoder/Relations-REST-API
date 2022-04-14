package com.jc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jc.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}