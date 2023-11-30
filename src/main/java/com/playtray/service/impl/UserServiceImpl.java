package com.playtray.service.impl;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        String username = userRegisterDTO.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            User user = mapToUser(userRegisterDTO);

            userRepository.save(user);
        }
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NullPointerException("User with username " + username + " doesn't exist!"));

        String rawPassword = userLoginDTO.getPassword();
        String encodedPassword = user.getPassword();

        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            user.setActive(true);
            return true;
        }

        return false;
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User with id " + id + " doesn't exist!"));

        userRepository.delete(user);
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
                .setCart(new Cart().setCustomer(user));

        return user;
    }

    @Override
    public boolean isUniqueUsername(String value) {
        return userRepository.findByUsername(value).isEmpty();
    }

    @Override
    public boolean isUniqueEmail(String value) {
        return userRepository.findByEmail(value).isEmpty();
    }

    @Override
    public void save(User customer) {
        userRepository.save(customer);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist!"));
    }
}
