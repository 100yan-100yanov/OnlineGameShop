package com.playtray.service.impl;

import com.playtray.model.dto.UserDTO;
import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.UserService;
import com.playtray.error.ObjectNotFoundException;
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
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User with id " + id + " doesn't exist!"));

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
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " doesn't exist!"));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }

    @Override
    public void removeUserRole(String username, Long roleId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " doesn't exist!"));

        user.getRoles().removeIf(userRole -> userRole.getId().equals(roleId));

        userRepository.save(user);
    }

    @Override
    public void addUserRole(String username, Long roleId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "User with username " + username + " doesn't exist!"));

        Role roleToAdd = roleRepository.findById(roleId)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Role with id " + roleId + " doesn't exist!"));;

        for (Role role : user.getRoles()) {
            if (role.getId().equals(roleId)) {
                throw new IllegalArgumentException(
                        "Role " + role.getName().name() + " already assigned to user " + username);
            }
        }
        user.getRoles().add(roleToAdd);

        userRepository.save(user);
    }
}
