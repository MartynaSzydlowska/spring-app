package com.example.blocks.core;

import com.example.blocks.persistance.BlockEntity;
import com.example.blocks.persistance.BlocksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BlocksServiceImpl implements BlocksService {

    BlocksRepository blocksRepository;

    @Override
    public BlockDto createBlock(BlockCreateDto blockCreateDo) {

        BlockEntity blockEntity = new BlockEntity();
        blockEntity.setId(UUID.randomUUID());
        blockEntity.setStartDate(blockCreateDo.getStartDate());
        blockEntity.setStartDate(blockCreateDo.getStartDate());
        blockEntity.setNote(blockCreateDo.getNote());
        blockEntity.setState(BlockState.ACTIVE);

        blocksRepository.save(blockEntity);

        BlockDto blockDto = convert(blockEntity);
        return blockDto;
    }

    private BlockDto convert(BlockEntity savedBlockEntity) {
        BlockDto blockDto = new BlockDto(savedBlockEntity.getId(),
                savedBlockEntity.getStartDate(),
                savedBlockEntity.getEndDate(),
                savedBlockEntity.getNote(),
                savedBlockEntity.getState());
        return blockDto;
    }

    @Override
    public BlockDto updateBlock(BlockDto blockUpdateDto) {
        return null;
    }

    @Override
    public Optional<BlockDto> getBooking(UUID id) {
        return Optional.empty();
    }
}
