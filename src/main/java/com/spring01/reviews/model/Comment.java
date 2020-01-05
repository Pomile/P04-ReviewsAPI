package com.spring01.reviews.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Document("comments")
public class Comment {

    @BsonId
    private String _id;

    @NotNull(message = "Title field must be provided")
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Summary field must be provided")
    @NotBlank(message = "Summary cannot be empty")
    private String text;

    private String reviewId;

    private ZonedDateTime zonedDateTime = ZonedDateTime.now();

    public Comment() {
    }

    public Comment(String title, String text) {
        this.title = title;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    public void setZonedDateTime(ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
