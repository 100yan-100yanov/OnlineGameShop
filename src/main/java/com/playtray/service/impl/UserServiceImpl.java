package com.playtray.service.impl;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.User;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        Optional<User> user = userRepository.findByUsername(userRegisterDTO.getUsername());

        if (user.isPresent()) {
            return false;
        }

        User userToSave = modelMapper.map(userRegisterDTO, User.class);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));

        userRepository.save(userToSave);
        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = user.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {

                //TODO

                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {

        //TODO
    }

    @Override
    public boolean isUserLogged() {
        //TODO
        return false;
    }
}
