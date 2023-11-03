package com.playtray.model.entity.user;

import com.playtray.model.entity.product.Game;
import com.playtray.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String lastName;

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 16)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany
    private List<Game> sellingGames;

    @OneToMany
    private List<Game> boughtGames;

    @OneToMany
    private List<Game> gamesInCart;
}
