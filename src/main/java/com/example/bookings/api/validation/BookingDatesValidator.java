package com.example.bookings.api.validation;

import com.example.bookings.api.BookingRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookingDatesValidator implements ConstraintValidator<BookingDatesConstraint, BookingRequest> {
    @Override
    public boolean isValid(BookingRequest bookingRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (bookingRequest.getCheckInDate() == null || bookingRequest.getCheckOutDate() == null) {
            return true;
        }
        return bookingRequest.getCheckOutDate().isAfter(bookingRequest.getCheckInDate());
    }
}
