package com.playtray.service;

import com.playtray.model.dto.UserDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    void deleteUser(Long id);

    boolean isUniqueUsername(String value);

    boolean isUniqueEmail(String value);

    void saveUser(User customer);

    User findByUsername(String name);

    List<UserDTO> getAllUsers();

    void removeUserRole(String username, Long roleId);

    void addUserRole(String username, Long roleId);

    Map<String, BigDecimal> getTotalSales();
}
