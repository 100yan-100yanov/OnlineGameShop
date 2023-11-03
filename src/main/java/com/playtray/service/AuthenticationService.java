package com.playtray.service;

import com.playtray.model.dto.UserLoginDTO;

public interface AuthenticationService {
    void login(UserLoginDTO userLoginDTO);
}
