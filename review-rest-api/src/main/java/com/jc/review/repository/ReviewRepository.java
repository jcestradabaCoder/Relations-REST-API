package com.jc.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jc.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	
}