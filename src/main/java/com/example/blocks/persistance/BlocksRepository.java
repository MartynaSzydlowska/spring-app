package com.example.blocks.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlocksRepository extends CrudRepository<BlockEntity, UUID> {
}
