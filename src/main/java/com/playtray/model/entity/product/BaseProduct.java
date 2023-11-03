package com.playtray.model.entity.product;

import com.playtray.model.entity.user.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;

@MappedSuperclass
public class BaseProduct extends BaseEntity {

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;

    @OneToMany
    List<Rating> ratings;
}
