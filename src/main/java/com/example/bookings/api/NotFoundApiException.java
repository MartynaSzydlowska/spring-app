package com.example.bookings.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundApiException extends RuntimeException {
    public NotFoundApiException(String message) {
        super(message);
    }
}
