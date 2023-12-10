package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;
import com.playtray.model.enums.ProductCategory;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public final class ProductAddDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private ProductCategory category;

    @NotBlank
    private PlatformName platform;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Positive
    @NotBlank
    private BigDecimal price;

    @NotBlank
    @Size(min = 5, max = 200)
    private String summary;

    @NotBlank
    @Size(min = 5, max = 500)
    private String description;

    @NotBlank
    @Pattern(regexp = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public PlatformName getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformName platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
