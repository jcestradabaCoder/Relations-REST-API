package com.jc.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jc.library.entity.Library;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

}