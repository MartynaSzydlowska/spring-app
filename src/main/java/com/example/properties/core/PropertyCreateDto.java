package com.example.properties.core;

import lombok.Value;

import java.util.UUID;

@Value
public class PropertyCreateDto {
    UUID id;
    String name;
    Float price;
    Integer maxGuests;
}
