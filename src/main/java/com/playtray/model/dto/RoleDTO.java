package com.playtray.model.dto;

import com.playtray.model.enums.UserRole;

public class RoleDTO {

    private Long id;

    private UserRole name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
