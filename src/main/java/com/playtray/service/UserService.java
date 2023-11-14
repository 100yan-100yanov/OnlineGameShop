package com.playtray.service;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void login(UserLoginDTO userLoginDTO);

    void logout();

    boolean isUserLogged();

    boolean isUniqueUsername(String value);

    boolean isUniqueEmail(String value);
}
