package com.spring01.reviews.controller;

import com.spring01.reviews.model.Comment;
import com.spring01.reviews.model.Review;
import com.spring01.reviews.service.CommentService;
import com.spring01.reviews.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@Validated
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    @Autowired private CommentService commentService;
    @Autowired private ReviewService reviewService;

    public CommentsController(CommentService commentService, ReviewService reviewService) {
        this.commentService = commentService;
        this.reviewService = reviewService;
    }

    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@Valid @RequestBody Comment comment, @PathVariable("reviewId") String reviewId) {
        Optional<Review> review = reviewService.getReview(reviewId);
        if(review.isPresent()){
            comment.setReview_id(reviewId);
            Optional<Comment> comment1 = Optional.ofNullable(commentService.save(comment));
            return new ResponseEntity<Object>(comment1.get(), HttpStatus.CREATED);

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }
}