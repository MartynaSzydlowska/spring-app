package com.example.bookings.api;

import com.example.bookings.api.validation.BookingDatesConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@BookingDatesConstraint
public class BookingRequest {
    @NotNull
    @Min(1)
    private Integer guestsCount;
    @NotNull
    private LocalDate checkInDate;
    @NotNull
    private LocalDate checkOutDate;
    @NotNull
    @Email
    private String email;
}
