package com.example.bookings.core;

import com.example.bookings.api.BookingState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class BookingDto {
    @NotNull
    UUID id;
    @NotNull
    @Min(1)
    Integer guestsCount;
    @NotNull
    LocalDate checkInDate;
    @NotNull
    LocalDate checkOutDate;
    @NotNull
    @Email
    String email;
    @NotNull
    BookingState state;
}
