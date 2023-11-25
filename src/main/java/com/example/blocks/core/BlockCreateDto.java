package com.example.blocks.core;

import lombok.Value;

import java.time.LocalDate;

@Value
public class BlockCreateDto {
    LocalDate startDate;
    LocalDate endDate;
    String note;
}
