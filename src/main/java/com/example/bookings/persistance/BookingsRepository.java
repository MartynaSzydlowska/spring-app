package com.example.bookings.persistance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BookingsRepository extends CrudRepository<BookingEntity, UUID> {

    @Query(nativeQuery = true, value = """
            SELECT COUNT(*) > 0 FROM BOOKINGS
             WHERE BOOKINGS.checkInDate <= :checkOutDate AND :checkInDate < BOOKINGS.checkOutDate
             AND BOOKINGS.state = 'BOOKED'
            """) //TODO sprawdz czy nowa rezerwacja moze zaczynac sie w dniu zak. innej   ---> NIE MOZE OTO FIX
    boolean hasOverlappingBookings(@Param("checkInDate") LocalDate checkInDate,
                                   @Param("checkOutDate") LocalDate checkOutDate);

    @Query(nativeQuery = true, value = """
            SELECT COUNT(*) > 0 FROM BOOKINGS
             WHERE BOOKINGS.checkInDate <= :checkOutDate AND :checkInDate <= BOOKINGS.checkOutDate
             AND BOOKINGS.state = 'BOOKED' AND BOOKINGS.id != :id
            """)
    boolean hasOverlappingBookings(@Param("checkInDate") LocalDate checkInDate,
                                   @Param("checkOutDate") LocalDate checkOutDate,
                                   @Param("id") UUID id);
}
