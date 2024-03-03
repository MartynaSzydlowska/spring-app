package com.example.properties.core;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.Column;
import lombok.Value;

import java.util.UUID;

@Value
public class PropertyDto {
    UUID id;
    String name;
    Float price;
    Integer maxGuests;
}
