package com.example.bookings.persistance;

import com.example.bookings.api.BookingState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@Table(name = "BOOKINGS")
public class BookingEntity {

    @Id
    private UUID id;
    @Column(nullable = false)
    private Integer guestsCount;
    @Column(nullable = false, name = "CHECKINDATE")
    @Temporal(TemporalType.DATE)
    private LocalDate checkInDate;
    @Column(nullable = false, name = "CHECKOUTDATE")
    @Temporal(TemporalType.DATE)
    private LocalDate checkOutDate;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private BookingState state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
