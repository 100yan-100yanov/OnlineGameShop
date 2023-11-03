package com.playtray.model.entity.product;

import com.playtray.model.entity.user.User;
import com.playtray.model.enums.PlatformName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "games")
public class Game extends BaseProduct {

    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50)
    private String name;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PlatformName platform; // TODO alternative name? change to class?

    @Column(nullable = false)
    @ManyToMany
    private List<GameGenre> categories;

    @ManyToOne
    private User offeredBy;
}
