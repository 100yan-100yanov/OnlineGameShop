package com.playtray.model.dto;

import com.playtray.model.enums.ProductCategory;
import com.playtray.model.enums.PlatformName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductAddDTO(@NotNull
                            ProductCategory productCategory,
                            @NotNull
                            PlatformName platform,
                            @NotBlank
                            @Size(min = 2, max = 50)
                            String name,
                            @Positive
                            @NotNull
                            BigDecimal price,
                            @NotBlank
                            @Size(min = 5, max = 200)
                            String description) {

}
