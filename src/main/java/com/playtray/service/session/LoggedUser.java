package com.playtray.service.session;

import com.playtray.model.entity.user.Role;
import com.playtray.model.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class LoggedUser {

    private String username;
    private Role role;
    private boolean isLogged;

    public void logout() {
        this.setUsername(null);
        this.setRole(null);
        this.setLogged(false);
    }

    public boolean isAdmin() {

        return this.role.getName().equals(UserRole.ADMIN);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
