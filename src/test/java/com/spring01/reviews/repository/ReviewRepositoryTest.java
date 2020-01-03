package com.spring01.reviews.repository;


import com.spring01.reviews.config.DataConfig;
import com.spring01.reviews.model.Review;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@EnableAutoConfiguration
public class ReviewRepositoryTest {

    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private ReviewRepository reviewRepository;
    private AnnotationConfigApplicationContext configApplicationContext;
    private Review review;

    @BeforeAll
    public void setDown(){
        System.err.println("I should run before all test cases");
        reviewRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() {
        configApplicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        Review review = configApplicationContext.getBean("reviewBean", Review.class);
        this.review = review;

    }

    @DisplayName("given object to save"
            + " when save object using MongoDB repository"
            + " then object is saved")
    @Test
    public void createProductReview() {
        Review res = saveReview();
        assertThat(res.getProductId()).isEqualTo(1L);
    }

    @DisplayName("Should return a list of product reviews")
    @Test
    public  void productReviews(){
        saveReview();
        Optional<List<Review>> reviews = reviewRepository.findAllByProductId(1L);
        assertThat(reviews.get().size()).isEqualTo(2);
    }

    private Review saveReview(){
        Review newReview = review;
        newReview.setProductId(1L);
        return reviewRepository.save(newReview);
    }
}