package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;
import com.playtray.model.enums.ProductCategory;

import java.math.BigDecimal;

public record ProductSummaryDTO(Long id,
                                String name,
                                PlatformName platform,
                                BigDecimal price,
                                String description,
                                String imageUrl) {
}
