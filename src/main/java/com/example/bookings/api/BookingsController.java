package com.example.bookings.api;

import com.example.bookings.core.BookingCreateDto;
import com.example.bookings.core.BookingDto;
import com.example.bookings.core.BookingsService;
import com.example.bookings.core.OverlappingBookingsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BookingsController {
    private final BookingsService bookingsService;

    public BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @PostMapping("/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(@RequestBody @Valid BookingRequest request) throws OverlappingBookingsException {

        BookingCreateDto bookingCreateDto = new BookingCreateDto(
                request.getGuestsCount(),
                request.getCheckInDate(),
                request.getCheckOutDate(),
                request.getEmail()
        );
        BookingDto serviceResponse = bookingsService.createBooking(bookingCreateDto);

        BookingResponse bookingResponse = convert(serviceResponse);

        return bookingResponse;
    }

    @GetMapping("/bookings")
    public List<BookingResponse> getBookings() {
        BookingResponse bookingResponse = new BookingResponse();

        return List.of(bookingResponse);
    }

    @GetMapping("/bookings/{id}")
    public BookingResponse getBooking(@PathVariable UUID id) {
        return bookingsService.getBooking(id)
                .map(this::convert)
                .orElseThrow(() -> new NotFoundApiException("Given id not found"));
    }

    private BookingResponse convert(BookingDto serviceResponse) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setState(serviceResponse.getState());
        bookingResponse.setId(serviceResponse.getId());
        bookingResponse.setEmail(serviceResponse.getEmail());
        bookingResponse.setCheckInDate(serviceResponse.getCheckInDate());
        bookingResponse.setCheckOutDate(serviceResponse.getCheckOutDate());
        bookingResponse.setGuestsCount(serviceResponse.getGuestsCount());
        return bookingResponse;
    }

    @PatchMapping("/bookings/{id}")
    public BookingResponse updateBooking(@PathVariable UUID id) {
        BookingResponse bookingResponse = new BookingResponse();

        return bookingResponse;
    }
}
