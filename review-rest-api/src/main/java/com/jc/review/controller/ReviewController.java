package com.jc.review.controller;

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

import com.jc.review.entity.Review;
import com.jc.review.exception.ResourceNotFoundException;
import com.jc.review.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;
	
	
	@PostMapping("/reviews")
	public Review createReview(@Valid @RequestBody Review review) {
		return reviewRepository.save(review);
	}
	
	@GetMapping("/reviews")
	public Page<Review> getAllReviews(Pageable pageable) {
		return reviewRepository.findAll(pageable);
	}
	
	@GetMapping("/reviews/{idReview}")
	private Review getReviewById(@PathVariable(name = "idReview") Long idReview) {
		return reviewRepository.findById(idReview).orElseThrow(() -> new ResourceNotFoundException("Review with id: " + idReview + " not found!"));
	}
	
	@PutMapping("/reviews/{idReview}")
	public Review updateReview(@PathVariable(name = "idReview") Long idReview, @Valid @RequestBody Review reviewRequest) {
		return reviewRepository.findById(idReview).map(review -> {
			review.setTitle(reviewRequest.getTitle());
			review.setDescription(reviewRequest.getDescription());
			review.setContent(reviewRequest.getContent());
			return reviewRepository.save(review);
		}).orElseThrow(() -> new ResourceNotFoundException("Review with id: " + idReview + " not found!"));
	}
	
	@DeleteMapping("/reviews/{idReview}")
	private ResponseEntity<?> deleteReview(@PathVariable(name = "idReview") Long idReview) {
		return reviewRepository.findById(idReview).map(review -> {
			reviewRepository.delete(review);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Review with id: " + idReview + " not found!"));
	}	
}