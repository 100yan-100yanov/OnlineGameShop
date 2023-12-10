package com.playtray.model.dto;

import com.playtray.model.enums.PlatformName;

import java.math.BigDecimal;

public class ProductLatestDTO {
    private Long id;
    private String name;
    private PlatformName platform;
    private BigDecimal price;
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

    public PlatformName getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformName platform) {
        this.platform = platform;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
