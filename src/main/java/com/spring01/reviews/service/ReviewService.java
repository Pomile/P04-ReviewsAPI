package com.spring01.reviews.service;

import com.spring01.reviews.model.Review;
import com.spring01.reviews.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /**
     ** Get a of product reviews
     * @param productId a product id
     * @return a list product reviews in the repository
     */

    public Optional<List<Review>> getProductReviews(Long productId){
        return reviewRepository.findAllByProductId(productId);
    }

    /**
     ** Get a review
     * @param reviewId a product id
     * @return a list product reviews in the repository
     */

    public Optional<Review> getReview(String reviewId){
        return reviewRepository.findById(reviewId);
    }
}