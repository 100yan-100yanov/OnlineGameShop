package com.playtray.utils;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;

    public User createTestUser(String username, String email) {
        return createUser(username, email, List.of(new Role().setName(UserRole.USER)));
    }

    public User createTestAdmin(String username) {
        return createUser(username, "testAdmin@email.com", List.of(new Role().setName(UserRole.ADMIN)));
    }

    private User createUser(String username, String email, List<Role> roles) {

        User user = new User();

        user
                .setUsername(username)
                .setPassword("password")
                .setEmail(email)
                .setFirstName("TestFirst")
                .setLastName("TestLast")
                .setActive(false)
                .setRoles(roles)
                .setCart(new Cart().setCustomer(user));

        return userRepository.save(user);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
