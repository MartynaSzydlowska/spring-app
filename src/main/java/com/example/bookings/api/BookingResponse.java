package com.example.bookings.api;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingResponse {
    private UUID id;
    private Integer guestsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String email;
    private BookingState state;
}
