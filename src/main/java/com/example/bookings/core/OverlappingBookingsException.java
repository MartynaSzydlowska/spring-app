package com.example.bookings.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OverlappingBookingsException extends Exception {
    public OverlappingBookingsException(String message) {
        super(message);
    }
}
