package com.playtray.model.entity.product;

import com.playtray.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
