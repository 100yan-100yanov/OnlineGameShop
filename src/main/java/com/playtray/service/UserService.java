package com.playtray.service;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.User;

import java.security.Principal;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void delete(Long id);

    boolean isUniqueUsername(String value);

    boolean isUniqueEmail(String value);

    void save(User customer);

    User findByUsername(String name);
}
