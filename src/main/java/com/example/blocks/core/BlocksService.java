package com.example.blocks.core;

import java.util.Optional;
import java.util.UUID;

public interface BlocksService {
    BlockDto createBlock(BlockCreateDto blockCreateDo);

    BlockDto updateBlock(BlockDto blockUpdateDto);

    Optional<BlockDto> getBooking(UUID id);
}
