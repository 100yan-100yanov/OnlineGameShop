package com.playtray.model.entity.product;

import com.playtray.model.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class GameGenre extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;
}
