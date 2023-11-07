package com.playtray.service.impl;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import com.playtray.service.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        Optional<User> user = this.userRepository.findByUsername(userRegisterDTO.getUsername());

        if (user.isPresent()) {
            return false;
        }

        this.userRepository.save(this.modelMapper.map(userRegisterDTO, User.class));
        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();

        Optional<User> user = this.userRepository.findByUsername(username);

        if (user.isPresent()) {
            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = user.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                Role role = user.get().getRole();

                loggedUser.setUsername(username);
                loggedUser.setLogged(true);
                loggedUser.setRole(role);

                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }

    @Override
    public boolean isUserLogged() {
        return this.loggedUser.isLogged();
    }
}
