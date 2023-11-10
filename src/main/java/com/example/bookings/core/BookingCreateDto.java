package com.example.bookings.core;

import lombok.Value;

import java.time.LocalDate;

@Value
public class BookingCreateDto {
    Integer guestsCount;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String email;
}
