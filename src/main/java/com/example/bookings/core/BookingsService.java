package com.example.bookings.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingsService {
    BookingDto createBooking(BookingCreateDto bookingCreateDto) throws NotAvailableSlotException;

    BookingDto updateBooking(BookingDto bookingUpdateDto) throws NotAvailableSlotException, BookingNotFoundException;

    Optional<BookingDto> getBooking(UUID id);
    List<BookingDto> getBookings();
}
