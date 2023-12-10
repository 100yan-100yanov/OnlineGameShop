package com.playtray.model.entity;

import com.playtray.model.enums.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole name;


    public UserRole getName() {
        return name;
    }

    public Role setName(UserRole name) {
        this.name = name;
        return this;
    }
}
