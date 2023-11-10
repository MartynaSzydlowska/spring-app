package com.example.bookings.persistance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingsRepository extends CrudRepository<BookingEntity, UUID> {

    @Query(nativeQuery = true, value = "SELECT id from BOOKINGS " +
            "where BOOKINGS.checkInDate <= :checkOutDate and :checkInDate <= BOOKINGS.checkOutDate")
    public ArrayList<UUID> getOverlappingBookings(@Param("checkInDate") LocalDate checkInDate,
                                                  @Param("checkOutDate") LocalDate checkOutDate);
}
