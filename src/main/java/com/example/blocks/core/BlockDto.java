package com.example.blocks.core;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class BlockDto {
    UUID id;
    LocalDate startDate;
    LocalDate endDate;
    String note;
    BlockState blockstate;
}
