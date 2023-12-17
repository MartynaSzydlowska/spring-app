package com.example.blocks.persistance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface BlocksRepository extends CrudRepository<BlockEntity, UUID> {

    @Query(nativeQuery = true, value = """
            SELECT COUNT(*) > 0 FROM BLOCKS
             WHERE BLOCKS.startDate <= :endDate AND :startDate <= BLOCKS.endDate
            """)
    boolean hasBlocksInTimeRange(@Param("startDate") LocalDate startDate,
                                   @Param("endDate") LocalDate endDate);

}
