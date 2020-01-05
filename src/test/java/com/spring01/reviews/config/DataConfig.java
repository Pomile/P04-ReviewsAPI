package com.spring01.reviews.config;

import com.spring01.reviews.model.Comment;
import com.spring01.reviews.model.Product;
import com.spring01.reviews.model.Review;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public Comment commentBean(){

        Comment comment = new Comment();
        comment.setTitle("Why did you say Iphones are great?");
        comment.setText("I just love iphone graphics");
        comment.setReviewId("5e0f575181b24e25f8d4c78c");

        return comment;
    }
    @Bean
    public Review reviewBean(){
        Review review = new Review();
        review.setTitle("iPhones are great");
        review.setSummary("My experience with i phone is awesome");

        return review;
    }
    @Bean
    public Product productBean(){
        Product product = new Product();
        product.setName("Blue band");
        product.setDescription("Nutritious Margarine");
        product.setPrice(700.00);
        product.setStock(20);
        product.setImage("blueBand.jpg");
        product.setProductCode("44344AB");

        return  product;
    }
}