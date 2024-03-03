package com.example.properties.api;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data

public class PropertyResponse {
    @Id
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private Float price;
    @Min(1)
    private Integer maxGuests;
}
