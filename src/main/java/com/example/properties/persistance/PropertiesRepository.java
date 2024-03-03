package com.example.properties.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertiesRepository extends CrudRepository<PropertyEntity, UUID> {
}
