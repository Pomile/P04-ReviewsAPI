package com.spring01.reviews.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ClientError {
    private ZonedDateTime timestamp;
    private Integer status;
    private HttpStatus  error;
    private List<?> errors;



    public ClientError(Integer status, HttpStatus error, List<?> message) {
        this.status = status;
        this.errors = message;
        this.error = error;
        this.timestamp = ZonedDateTime.now();
    }

}