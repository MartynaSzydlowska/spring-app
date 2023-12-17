package com.example.blocks.persistance;

import com.example.blocks.core.BlockState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "BLOCKS")
public class BlockEntity {
    @Id
    private UUID id;
    @Column(nullable = false, name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;
    @Column(nullable = false, name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;
    @Column
    private String note;
    @Column
    private BlockState state;
}
