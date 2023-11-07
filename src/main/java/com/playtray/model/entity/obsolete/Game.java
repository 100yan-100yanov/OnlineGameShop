package com.playtray.model.entity.obsolete;

import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.model.enums.PlatformName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "games")
public class Game extends Product {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlatformName getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformName platform) {
        this.platform = platform;
    }

    public List<GameGenre> getCategories() {
        return categories;
    }

    public void setCategories(List<GameGenre> categories) {
        this.categories = categories;
    }

    public User getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(User offeredBy) {
        this.offeredBy = offeredBy;
    }
}
