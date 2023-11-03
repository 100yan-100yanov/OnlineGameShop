package com.playtray.model.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "consoles")
public class Console extends BaseProduct {

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String brand;

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 30)
    private String model;

}
