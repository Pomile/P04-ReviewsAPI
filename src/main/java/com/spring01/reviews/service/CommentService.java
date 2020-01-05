package com.spring01.reviews.service;

import com.spring01.reviews.model.Comment;
import com.spring01.reviews.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService{
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {

        this.commentRepository = commentRepository;
    }

    /**
     ** creates a comment
     * @param comment A comment object, which should be new
     * @return the review with the new comment in a list
     */
    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    /**
     ** Get a list product reviews
     * @param reviewId a review id
     * @return a list product reviews in the repository
     */

    public Optional<List<Comment>> getReviewComments(String reviewId){
        System.err.println(reviewId);
        return commentRepository.findAllByReviewId(reviewId);
    }
}