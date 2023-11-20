package com.example.bookings.core;

import java.util.Optional;
import java.util.UUID;

public interface BookingsService {
    BookingDto createBooking(BookingCreateDto bookingCreateDto) throws OverlappingBookingsException;

    BookingDto updateBooking(BookingDto bookingUpdateDto) throws OverlappingBookingsException, BookingNotFoundException;

    Optional<BookingDto> getBooking(UUID id);
}
