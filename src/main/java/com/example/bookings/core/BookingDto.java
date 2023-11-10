package com.example.bookings.core;

import com.example.bookings.api.BookingState;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class BookingDto {
    UUID id;
    Integer guestsCount;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String email;
    BookingState state;
}
