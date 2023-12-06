package com.playtray.service;

import com.playtray.model.dto.UserDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;

import java.util.List;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void delete(Long id);

    boolean isUniqueUsername(String value);

    boolean isUniqueEmail(String value);

    void save(User customer);

    User findByUsername(String name);

    List<UserDTO> getAllUsers();

    void removeUserRole(String username, Long roleId);

    void addUserRole(String username, UserRole roleName);
}
