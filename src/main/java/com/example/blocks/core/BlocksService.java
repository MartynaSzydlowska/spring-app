package com.example.blocks.core;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface BlocksService {
    BlockDto createBlock(BlockCreateDto blockCreateDo);

    BlockDto updateBlock(BlockDto blockUpdateDto);

    Optional<BlockDto> getBooking(UUID id);

    boolean hasBlocksInTimeRange(LocalDate startDate, LocalDate endDate);
}
