package com.example.bookings.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotAvailableSlotException extends Exception {
    public NotAvailableSlotException(String message) {
        super(message);
    }
}
