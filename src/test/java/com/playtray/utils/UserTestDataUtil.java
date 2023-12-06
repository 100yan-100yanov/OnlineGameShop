package com.playtray.utils;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createAdmin() {
        User admin = new User()
                .setUsername("Admin")
                .setPassword(passwordEncoder.encode("TestPass"))
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setEmail("admin@email.com")
                .setActive(true)
                .setRoles(roleRepository.findAll());

        return userRepository.save(admin);
    }

    public User createUser(String username) {
        User user = new User();
        Role role = roleRepository.findByName(UserRole.USER);
        Cart cart = new Cart().setCustomer(user);

        user
                .setUsername(username)
                .setPassword(passwordEncoder.encode("TestPass"))
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("mail@email.com")
                .setCart(cart)
                .setActive(true)
                .setRoles(List.of(role));

        return userRepository.save(user);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
