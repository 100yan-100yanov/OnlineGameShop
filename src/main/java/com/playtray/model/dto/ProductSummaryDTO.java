package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;

import java.math.BigDecimal;

public record ProductSummaryDTO(Long id,
                                String name,
                                PlatformName platform,
                                BigDecimal price,
                                String summary,
                                String imageUrl) {
}
