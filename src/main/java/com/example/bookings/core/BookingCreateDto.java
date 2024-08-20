package com.example.bookings.core;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BookingCreateDto {
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
}
