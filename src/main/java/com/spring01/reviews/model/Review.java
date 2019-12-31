package com.spring01.reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;


import java.lang.annotation.Documented;
import java.util.List;


@Document("reviews")
public class Review {
    @BsonId
    private String _id;

    @NotNull(message = "Title field must be provided")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Summary field must be provided")
    @NotBlank(message = "Summary cannot be empty")
    private String summary;
    private String details = "";
    private Long productId;

    public Review() { }

    public Review(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
