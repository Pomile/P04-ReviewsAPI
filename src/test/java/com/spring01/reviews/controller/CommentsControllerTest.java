package com.spring01.reviews.controller;

import com.spring01.reviews.config.DataConfig;
import com.spring01.reviews.model.Comment;
import com.spring01.reviews.model.Review;
import com.spring01.reviews.service.CommentService;
import com.spring01.reviews.service.ProductService;
import com.spring01.reviews.service.ReviewService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@EnableSpringDataWebSupport
class CommentsControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired private JacksonTester<Comment> json;
    @MockBean private ReviewService reviewService;
    @MockBean private CommentService commentService;
    private AnnotationConfigApplicationContext configApplicationContext;
    private Comment comment;

    @BeforeEach
    void setUp() {
        configApplicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        Review review = configApplicationContext.getBean("reviewBean", Review.class);
        Comment comment = configApplicationContext.getBean("commentBean", Comment.class);
        this.comment = comment;
        review.set_id("5e0f575181b24e25f8d4c78c");
        comment.set_id("5e11a13bc31d8925c97d9bc9");
        given(reviewService.getReview(any())).willReturn(java.util.Optional.of(review));
        given(commentService.save(any())).willReturn(comment);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void createCommentForReview() throws Exception {
        comment.set_id(null);
        mvc.perform(post(new URI("/comments/reviews/5e0f575181b24e25f8d4c78c"))
        .content(json.write(comment).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.review_id").value("5e0f575181b24e25f8d4c78c")
        );
    }
}