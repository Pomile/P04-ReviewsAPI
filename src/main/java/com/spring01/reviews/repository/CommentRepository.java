package com.spring01.reviews.repository;

import com.spring01.reviews.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<List<Comment>> findAllByReviewId(String s);
}
