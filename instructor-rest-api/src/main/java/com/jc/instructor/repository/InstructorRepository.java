package com.jc.instructor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jc.instructor.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}