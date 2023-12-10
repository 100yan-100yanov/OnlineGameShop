package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;

import java.math.BigDecimal;

public final class ProductSummaryDTO {
    private Long id;
    private String name;
    private PlatformName platform;
    private BigDecimal price;
    private String summary;
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformName getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformName platform) {
        this.platform = platform;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
