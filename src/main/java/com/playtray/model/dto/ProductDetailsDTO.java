package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;

import java.math.BigDecimal;

public record ProductDetailsDTO(Long id,
                                String name,
                                PlatformName platform,
                                BigDecimal price,
                                String description,
                                String imageUrl) {
}
