package com.playtray.model.entity.product;

import com.playtray.model.enums.AccessoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "accessories")
public class Accessory extends Product {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private AccessoryType type;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String brand;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String model;

    public AccessoryType getType() {
        return type;
    }

    public void setType(AccessoryType type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
