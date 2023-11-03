package com.playtray.model.entity.product;

import com.playtray.model.entity.BaseEntity;
import com.playtray.model.enums.Category;
import com.playtray.model.enums.Condition;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@MappedSuperclass
public class BaseProduct extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;

    @Column(nullable = false)
    private String image;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Enumerated(EnumType.STRING)
    private Category category; //TODO is it needed?

    @Column(nullable = false)
    @DecimalMin("5")
    @DecimalMax("5000")
    private BigDecimal price;

}
