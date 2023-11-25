package com.playtray.model.dto;

import com.playtray.model.enums.ProductCategory;
import com.playtray.model.enums.PlatformName;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductAddDTO(@NotNull
                            Long id,
                            @NotNull
                            ProductCategory category,
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
                            String summary,
                            @NotBlank
                            @Size(min = 5, max = 500)
                            String description,
                            @NotBlank
                            @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
                            String imageUrl) {

}
