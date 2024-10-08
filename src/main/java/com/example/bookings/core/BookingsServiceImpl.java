package com.example.bookings.core;

import com.example.blocks.core.BlocksServiceImpl;
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
    private final BlocksServiceImpl blocksService;

    public BookingsServiceImpl(BookingsRepository bookingsRepository, BlocksServiceImpl blocksService) {
        this.bookingsRepository = bookingsRepository;
        this.blocksService = blocksService;
    }

    @Override
    public BookingDto createBooking(BookingCreateDto bookingCreateDto) throws NotAvailableSlotException {
        LocalDate checkInDate = bookingCreateDto.getCheckInDate();
        LocalDate checkOutDate = bookingCreateDto.getCheckOutDate();
        boolean overlappingBookings = bookingsRepository.hasOverlappingBookings(checkInDate, checkOutDate);
        boolean blocksInTimeRange = blocksService.hasBlocksInTimeRange(checkInDate, checkOutDate);
        if (overlappingBookings || blocksInTimeRange) {
            throw new NotAvailableSlotException("The apartment is already booked for given dates");
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

    @Override
    public BookingDto updateBooking(BookingDto bookingUpdateDto) throws NotAvailableSlotException, BookingNotFoundException {
        BookingEntity bookingEntity = bookingsRepository.findById(bookingUpdateDto.getId())
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

        if (bookingUpdateDto.getState() != BookingState.CANCELLED) {
            LocalDate checkInDate = bookingUpdateDto.getCheckInDate();
            LocalDate checkOutDate = bookingUpdateDto.getCheckOutDate();
            boolean overlappingBookings = bookingsRepository.hasOverlappingBookings(checkInDate, checkOutDate, bookingEntity.getId());
            if (overlappingBookings) {
                throw new NotAvailableSlotException("The apartment is already booked for given dates");
            }
        }

        bookingEntity.setCheckInDate(bookingUpdateDto.getCheckInDate());
        bookingEntity.setCheckOutDate(bookingUpdateDto.getCheckOutDate());
        bookingEntity.setEmail(bookingUpdateDto.getEmail());
        bookingEntity.setGuestsCount(bookingUpdateDto.getGuestsCount());
        bookingEntity.setState(bookingUpdateDto.getState());

        BookingEntity savedBookingEntity = bookingsRepository.save(bookingEntity);

        BookingDto bookingDto = convert(savedBookingEntity);
        return bookingDto;
    }

    public List<BookingDto> getBookings() {
        Iterable<BookingEntity> bookings = bookingsRepository.findAll();
        ArrayList<BookingDto> result = new ArrayList<>();
        bookings.forEach(entity -> convert(entity));
        bookings.forEach(booking -> result.add(convert(booking)));

        return result;
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
