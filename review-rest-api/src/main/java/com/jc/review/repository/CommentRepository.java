package com.jc.review.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jc.review.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	Page<Comment> findByReviewId(Long reviewId, Pageable pageable);
	Optional<Comment> findByIdAndReviewId(Long comentarioId, Long reviewId);
}