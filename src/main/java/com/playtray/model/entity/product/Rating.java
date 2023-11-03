package com.playtray.model.entity.product;

import com.playtray.model.entity.user.BaseEntity;
import com.playtray.model.entity.user.User;
import com.playtray.model.enums.RatingScale;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ratings")
public class Rating extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    private RatingScale scale;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String comment;

    @ManyToOne
    private User ratedBy;
}
