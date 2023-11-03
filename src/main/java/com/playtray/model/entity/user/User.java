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
    @Size(min = 4, max = 20)
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Game> getSellingGames() {
        return sellingGames;
    }

    public void setSellingGames(List<Game> sellingGames) {
        this.sellingGames = sellingGames;
    }

    public List<Game> getBoughtGames() {
        return boughtGames;
    }

    public void setBoughtGames(List<Game> boughtGames) {
        this.boughtGames = boughtGames;
    }

    public List<Game> getGamesInCart() {
        return gamesInCart;
    }

    public void setGamesInCart(List<Game> gamesInCart) {
        this.gamesInCart = gamesInCart;
    }
}
