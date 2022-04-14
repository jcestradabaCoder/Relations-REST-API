package com.jc.instructor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc.instructor.entity.Instructor;
import com.jc.instructor.exception.ResourceNotFoundException;
import com.jc.instructor.repository.InstructorRepository;

@RestController
@RequestMapping("/api")
public class InstructorController {
	
	@Autowired
	private InstructorRepository instructorRepository;
	
	@PostMapping("/instructors")
	public Instructor createInstructor(@Valid @RequestBody Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	
	@GetMapping("/instructors")
	public List<Instructor> getAllInstructors() {
		return instructorRepository.findAll();
	}
	
	@GetMapping("instructors/{idInstructor}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable(name = "idInstructor") Long instructorId) {
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor with id: " + instructorId + " not found!"));
		
		return ResponseEntity.ok().body(instructor);
	}
	
	@PutMapping("instructors/{idInstructor}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable(name = "idInstructor") Long instructorId, @Valid @RequestBody Instructor instructorRequest) {
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor with id: " + instructorId + " not found!"));

		instructor.setPhoneNumber(instructorRequest.getPhoneNumber());
		instructor.setEmail(instructorRequest.getEmail());
		
		Instructor updatedInstructor = instructorRepository.save(instructor);
		return ResponseEntity.ok().body(updatedInstructor);
	}
	
	@DeleteMapping("instructors/{idInstructor}")
	public Map<String, Boolean> deleteInstructor(@PathVariable(name = "idInstructor") Long instructorId) {
		Instructor instructor = instructorRepository.findById(instructorId)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor with id: " + instructorId + " not found!"));

		instructorRepository.delete(instructor);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Instructor deleted successfully!", Boolean.TRUE);
		return respuesta;
	}
}