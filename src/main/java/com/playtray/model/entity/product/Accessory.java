package com.playtray.model.entity.product;

import com.playtray.model.enums.AccessoryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "accessories")
public class Accessory extends BaseProduct {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private AccessoryType type;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String brand;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String model;
}
