package com.playtray.model.entity.product;

import com.playtray.model.entity.BaseEntity;
import com.playtray.model.enums.GenreName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "genres")
public class GameGenre extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private GenreName name;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 200)
    private String description;

    public GenreName getName() {
        return name;
    }

    public void setName(GenreName name) {
        this.name = name;
        this.setDescription(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(GenreName name) {
        String description = "";

        switch (name) {
            case ACTION:
                description = "Action games emphasize physical challenges, including hand–eye coordination and reaction time where the player must navigate a level, collecting objects, avoiding obstacles, and battling enemies with their natural skills as well as weapons and other tools at their disposal.";
                break;

            case ADVENTURE:
                description = "Adventure games have the player assume the role of a protagonist in an interactive story, driven by exploration and/or puzzle-solving.";
                break;

            case RPG:
                description = "RPG games have the player control the actions of a character (or several party members) immersed in some well-defined world by way of recording statistics.";
                break;

            case FIGHTING:
                description = "Fighting games involve combat between two or more characters. Fighting game combat often features mechanics such as blocking, grappling, counter-attacking, and chaining attacks together into \"combos\".";
                break;

            case STRATEGY:
                description = "Strategy games emphasize thinking and planning over direct instant action in order to achieve victory and focus on high-level strategy, logistics and resource management.";
                break;

            case HORROR:
                description = "Horror games focus on fear and attempt to scare the player via traditional horror fiction elements such as atmospherics, death, the undead, blood and gore.";
                break;

            case SPORT:
                description = "Sport games simulate the practice of sports. Most sports have been recreated with a video games, including team sports, track and field, extreme sports, and combat sports.";
                break;

            case RACING:
                description = "Racing games have the player participate in a racing competition. They may be based on anything from real-world racing leagues to fantastical settings. They are distributed along a spectrum between more realistic racing simulations and more fantastical arcade-style racing games.";
                break;
        }
        this.description = description;
    }
}
