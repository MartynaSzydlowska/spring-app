package com.example.properties.core;

import java.util.UUID;

public interface PropertiesService {
    PropertyDto createProperty(PropertyCreateDto propertyCreateDto);
    PropertyDto getProperty(UUID id);
    PropertyDto updateProperty(UUID id);
}
