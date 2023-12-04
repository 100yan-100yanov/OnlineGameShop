package com.playtray.utils;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.User;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser() {
        User user = new User();

        user
                .setUsername("TestUser")
                .setPassword(passwordEncoder.encode("TestPass"))
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("mail@email.com")
                .setActive(false)
                .setCart(new Cart().setCustomer(user))
                .setRoles(roleRepository.findAll());

        return userRepository.save(user);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
