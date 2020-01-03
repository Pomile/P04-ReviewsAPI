package com.spring01.reviews.service;

import com.spring01.reviews.model.Review;
import com.spring01.reviews.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService{
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     ** creates a review
     * @param review A product object, which should be new
     * @return the new product is stored in the repository
     */
    public Review save(Review review){
        return reviewRepository.save(review);
    }
}