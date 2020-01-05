package com.spring01.reviews.repository;

import com.spring01.reviews.config.DataConfig;
import com.spring01.reviews.config.MongoConfig;
import com.spring01.reviews.model.Comment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ContextConfiguration(classes = MongoConfig.class)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableAutoConfiguration
public class CommentRepositoryTest {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private CommentRepository commentRepository;
    private AnnotationConfigApplicationContext configApplicationContext;
    private Comment comment;

    @BeforeEach
    void setUp() {
        configApplicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        Comment comment= configApplicationContext.getBean("commentBean", Comment.class);
        this.comment = comment;
    }

    @DisplayName("given object to save"
            + " when save object using MongoDB repository"
            + " then object is saved")
    @Test
    public void save(){
        Comment comment = saveComment();
        assertEquals("5e0f575181b24e25f8d4c78c", comment.getReviewId());
    }

    @DisplayName("Get all review comments")
    @Test
    public void findAllByReviewId(){
        saveComment();
        Optional<List<Comment>> comments = commentRepository.findAllByReviewId(comment.getReviewId());
        assertEquals(2, comments.get().size());
    }

    private Comment saveComment(){
        Comment newComment = comment;
        ZonedDateTime time = ZonedDateTime.now(ZoneOffset.UTC);
        comment.setZonedDateTime(time);
        return commentRepository.save(newComment);
    }

}