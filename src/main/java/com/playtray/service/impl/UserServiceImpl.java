package com.playtray.service.impl;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        Optional<User> optionalUser = userRepository.findByUsername(userRegisterDTO.getUsername());

        if (optionalUser.isEmpty()) {
            User user = mapToUser(userRegisterDTO);

            userRepository.save(user);
        }
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = optionalUser.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {

                User user = optionalUser.get();
                user.setActive(true);
            }
        }
    }

    @Override
    public void logout() {

        //TODO
    }

    private User mapToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user
                .setUsername(userRegisterDTO.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .setEmail(userRegisterDTO.getEmail())
                .setFirstName(userRegisterDTO.getFirstName())
                .setLastName(userRegisterDTO.getLastName())
                .setActive(false)
                .setRoles(List.of(roleRepository.findByName(UserRole.USER)))
                .setBoughtProducts(new ArrayList<>())
                .setProductsInCart(new ArrayList<>());

        return user;
    }

    @Override
    public boolean isUserLogged() {
        //TODO
        return false;
    }

    @Override
    public boolean isUniqueUsername(String value) {
        return userRepository.findByUsername(value).isEmpty();
    }

    @Override
    public boolean isUniqueEmail(String value) {
        return userRepository.findByEmail(value).isEmpty();
    }
}
