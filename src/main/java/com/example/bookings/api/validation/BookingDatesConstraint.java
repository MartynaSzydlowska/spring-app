package com.example.bookings.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BookingDatesValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BookingDatesConstraint {
    String message() default "Check-out date should be after check-in date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}