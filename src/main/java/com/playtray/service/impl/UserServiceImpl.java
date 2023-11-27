package com.playtray.service.impl;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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
    public boolean login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            String rawPassword = userLoginDTO.getPassword();
            String encodedPassword = optionalUser.get().getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                User user = optionalUser.get();
                user.setActive(true);
                return true;
            }
        }

        return false;
    }

    @Override
    public void logout(Principal principal) {
      User user = userRepository
              .findByUsername(principal.getName())
              .orElseThrow(() -> new UsernameNotFoundException("User with username " + principal.getName() + " doesn't exist!"));

        user.setActive(false);
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NullPointerException("User with id " + id + " doesn't exist!");
        }

        userRepository.delete(user.get());
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
    public User findByUsername(String name) {
        return userRepository
                .findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + name + " doesn't exist!"));
    }

    @Override
    public void save(User customer) {
        userRepository.save(customer);
    }
}
