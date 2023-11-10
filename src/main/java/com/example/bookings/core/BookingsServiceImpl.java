package com.example.bookings.core;

import com.example.bookings.api.BookingState;
import com.example.bookings.persistance.BookingEntity;
import com.example.bookings.persistance.BookingsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingsServiceImpl implements BookingsService {
    private final BookingsRepository bookingsRepository;

    public BookingsServiceImpl(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    @Override
    public BookingDto createBooking(BookingCreateDto bookingCreateDto) throws OverlappingBookingsException {
        LocalDate checkInDate = bookingCreateDto.getCheckInDate();
        LocalDate checkOutDate = bookingCreateDto.getCheckOutDate();
        ArrayList<UUID> overlappingBookings = bookingsRepository.getOverlappingBookings(checkInDate, checkOutDate);
        if(overlappingBookings.size() > 0){
            throw new OverlappingBookingsException("The apartment is already booked for given dates");
        }

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setId(UUID.randomUUID());
        bookingEntity.setCheckInDate(bookingCreateDto.getCheckInDate());
        bookingEntity.setCheckOutDate(bookingCreateDto.getCheckOutDate());
        bookingEntity.setEmail(bookingCreateDto.getEmail());
        bookingEntity.setGuestsCount(bookingCreateDto.getGuestsCount());
        bookingEntity.setState(BookingState.BOOKED);

        BookingEntity savedBookingEntity = bookingsRepository.save(bookingEntity);

        BookingDto bookingDto = convert(savedBookingEntity);
        return bookingDto;
    }

    private BookingDto convert(BookingEntity savedBookingEntity) {
        BookingDto bookingDto = new BookingDto(savedBookingEntity.getId(),
                savedBookingEntity.getGuestsCount(),
                savedBookingEntity.getCheckInDate(),
                savedBookingEntity.getCheckOutDate(),
                savedBookingEntity.getEmail(),
                savedBookingEntity.getState());
        return bookingDto;
    }

    @Override
    public Optional<BookingDto> getBooking(UUID id) {
        return bookingsRepository.findById(id)
                .map(this::convert);
    }
}
