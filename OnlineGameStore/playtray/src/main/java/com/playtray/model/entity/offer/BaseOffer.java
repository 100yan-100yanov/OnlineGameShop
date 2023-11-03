package com.playtray.model.entity.offer;

import com.playtray.model.entity.BaseEntity;
import com.playtray.model.enums.Category;
import com.playtray.model.enums.Condition;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@MappedSuperclass
public class BaseOffer extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Category category; //TODO is it needed?

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Column(nullable = false)
    @DecimalMin("5")
    @DecimalMax("5000")
    private BigDecimal price;

}
