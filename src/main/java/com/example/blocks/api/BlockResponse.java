package com.example.blocks.api;

import com.example.blocks.core.BlockState;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BlockResponse {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
    private BlockState blockstate;
}
