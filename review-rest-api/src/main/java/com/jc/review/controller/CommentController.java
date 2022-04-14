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

import com.jc.review.entity.Comment;
import com.jc.review.exception.ResourceNotFoundException;
import com.jc.review.repository.CommentRepository;
import com.jc.review.repository.ReviewRepository;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	@PostMapping("/reviews/{idReview}/comments")
	public Comment createComment(@PathVariable(name = "idReview") Long idReview, @Valid @RequestBody Comment comment) {
		return reviewRepository.findById(idReview).map(review -> {
			comment.setReview(review);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Review with id: " + idReview + " not found!"));
	}
	
	@GetMapping("/reviews/{idReview}/comments")
	public Page<Comment> getAllCommentsFromReview(@PathVariable(name = "idReview") Long idReview, Pageable pageable) {
		return commentRepository.findByReviewId(idReview, pageable);
	}
	
	@GetMapping("/reviews/{idReview}/comments/{idComment}") 
	public Comment getCommentById(@PathVariable(name = "idReview") Long idReview, @PathVariable(name = "idComment") Long idComment) {		
		return commentRepository.findByIdAndReviewId(idComment, idReview).map(comment -> {
			return comment;
		}).orElseThrow(() -> new ResourceNotFoundException("Comment with id: " + idComment + " and Review with id: " + idReview + " not found"));
	}
	
	@PutMapping("/reviews/{idReview}/comments/{idComment}")
	public Comment updateComment(@PathVariable(name = "idReview") Long idReview, @PathVariable(name = "idComment") Long idComment, @Valid @RequestBody Comment commentRequest) {
		if(reviewRepository.existsById(idReview) == false) {
			throw new ResourceNotFoundException("Review with id: " + idReview + " not found!");
		}

		return commentRepository.findById(idComment).map(comment -> {
			comment.setText(commentRequest.getText());
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Comment with id: " + idComment + " not found!"));
	}
	
	@DeleteMapping("/reviews/{idReview}/comments/{idComment}") 
	public ResponseEntity<?> deleteComment(@PathVariable(name = "idReview") Long idReview, @PathVariable(name = "idComment") Long idComment) {		
		return commentRepository.findByIdAndReviewId(idComment, idReview).map(comment -> {
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Comment with id: " + idComment + " and Review with id: " + idReview + " not found"));
	}
}