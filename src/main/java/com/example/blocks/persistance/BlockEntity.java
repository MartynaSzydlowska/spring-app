package com.example.blocks.persistance;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "BLOCKADES")
public class BlockEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @Column
    private String note;
}
