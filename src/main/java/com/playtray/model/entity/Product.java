package com.playtray.model.entity;

import com.playtray.model.enums.ProductCategory;
import com.playtray.model.enums.PlatformName;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlatformName platform;

    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String name;

    @Column(nullable = false)
    @DecimalMin("5.00")
    @DecimalMax("5000.00")
    private BigDecimal price;

    @Column(nullable = false)
    @Size(min = 5, max = 180)
    private String summary;

    @Column(nullable = false)
    @Size(min = 5, max = 500)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    public ProductCategory getCategory() {
        return productCategory;
    }

    public Product setCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public PlatformName getPlatform() {
        return platform;
    }

    public Product setPlatform(PlatformName platform) {
        this.platform = platform;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Product setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
