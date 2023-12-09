package com.example.blocks.api;

import com.example.blocks.core.BlockCreateDto;
import com.example.blocks.core.BlockDto;
import com.example.blocks.core.BlocksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlocksController {
    private final BlocksService blocksService;

    public BlocksController(BlocksService blocksService) {
        this.blocksService = blocksService;
    }

    @PostMapping("/blocks")
    @ResponseStatus(HttpStatus.CREATED)
    public BlockResponse createBlock(@RequestBody @Valid BlockRequest request) {

        BlockCreateDto blockCreateDto = new BlockCreateDto(
                request.getStartDate(),
                request.getEndDate(),
                request.getNote()
        );
        BlockDto serviceResponse = blocksService.createBlock(blockCreateDto);

        BlockResponse blockResponse = convert(serviceResponse);

        return blockResponse;
    }

    private BlockResponse convert(BlockDto serviceResponse) {
        BlockResponse blockResponse = new BlockResponse();
        blockResponse.setId(serviceResponse.getId());
        blockResponse.setNote(serviceResponse.getNote());
        blockResponse.setBlockstate(serviceResponse.getBlockstate());
        blockResponse.setStartDate(serviceResponse.getStartDate());
        blockResponse.setEndDate(serviceResponse.getEndDate());
        return blockResponse;
    }
}
