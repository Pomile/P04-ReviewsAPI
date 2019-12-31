package com.spring01.reviews.controller;

import com.spring01.reviews.config.DataConfig;
import com.spring01.reviews.model.Product;
import com.spring01.reviews.model.Review;

import com.spring01.reviews.service.ProductService;
import com.spring01.reviews.service.ReviewService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

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
class ReviewsControllerTest {
    @Autowired private MockMvc mvc;
    @Autowired private JacksonTester<Review> json;
    @MockBean private ReviewService reviewService;
    @MockBean private ProductService productService;
    private AnnotationConfigApplicationContext configApplicationContext;
    private Review review;
    private Long productId;

    @BeforeEach
    void setUp() {
        configApplicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        Review review = configApplicationContext.getBean("reviewBean", Review.class);
        Product product = configApplicationContext.getBean("productBean", Product.class);
        this.review = review;
        this.productId = 1L;
        Review newReview = review;
        newReview.set_id("5e0b0ce65c54be4e05ac4998");
        newReview.setProductId(productId);
        product.setId(productId);
        given(productService.findById(1)).willReturn(java.util.Optional.of(product));
        given(reviewService.save(any())).willReturn(review);
    }

    @Test
    public void createReviewForProduct() throws Exception  {

        mvc.perform(post(new URI("/reviews/products/" + productId))
                .content(json.write(review).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(productId)
        );
    }

    @Test
    public void shouldNotCreateReviewForProductWithEmptyTitle() throws Exception {
        review.setTitle("");
        mvc.perform(post(new URI("/reviews/products/1"))
                .content(json.write(review).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].title").value("Title cannot be empty")
                );
    }

    @Test
    public void shouldNotcreateReviewForProductWithEmptySummary() throws Exception {
        review.setSummary("");
        mvc.perform(post(new URI("/reviews/products/1"))
                .content(json.write(review).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].summary").value("Summary cannot be empty")
                );
    }

    @Test
    public void shouldNotcreateReviewForProductWithNegativeNumber() throws Exception {

        mvc.perform(post(new URI("/reviews/products/-1"))
                .content(json.write(review).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0].-1").value("Product id must be greater than or equal to 1")
                );
    }

    @Test
    public void shouldNotcreateReviewForProductWithInvalid() throws Exception {

        mvc.perform(post(new URI("/reviews/products/hhghgdh"))
                .content(json.write(review).getJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.errors[0]").value("Invalid input for /hhghgdh")
                );
    }

}