package com.playtray.init;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class RolesInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RolesInit(RoleRepository roleRepository,
                     UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();

            Arrays.stream(UserRole.values())
                    .forEach(userRole -> {
                        Role role = new Role().setName(userRole);
                        roles.add(role);
                    });

            roleRepository.saveAll(roles);
        }

        List<User> DbUsers = userRepository.findAll();

        if (DbUsers.size() == 0) {
            User admin = new User();
            User test1 = new User();
            User test2 = new User();
            User test3 = new User();

            List<User> users = List.of(
                    admin
                            .setFirstName("Stoyan")
                            .setLastName("Stoyanov")
                            .setUsername("admin")
                            .setPassword(passwordEncoder.encode("666666"))
                            .setEmail("admin@playtray.com")
                            .setActive(true)
                            .setRoles(List.of(
                                    roleRepository.findByName(UserRole.ADMIN),
                                    roleRepository.findByName(UserRole.USER)))
                            .setCart(new Cart().setCustomer(admin)),

                    test1
                            .setFirstName("Test1")
                            .setLastName("Testov1")
                            .setUsername("test1")
                            .setPassword(passwordEncoder.encode("666666"))
                            .setEmail("test1@playtray.com")
                            .setActive(false)
                            .setRoles(List.of(
                                    roleRepository.findByName(UserRole.ADMIN),
                                    roleRepository.findByName(UserRole.USER)))
                            .setCart(new Cart().setCustomer(test1)),

                    test2
                            .setFirstName("Test2")
                            .setLastName("Testov2")
                            .setUsername("test2")
                            .setPassword(passwordEncoder.encode("666666"))
                            .setEmail("test2@playtray.com")
                            .setActive(false)
                            .setRoles(List.of(
                                    roleRepository.findByName(UserRole.USER)))
                            .setCart(new Cart().setCustomer(test2)),

                    test3
                            .setFirstName("Test3")
                            .setLastName("Testov3")
                            .setUsername("test3")
                            .setPassword(passwordEncoder.encode("666666"))
                            .setEmail("test3@playtray.com")
                            .setActive(false)
                            .setRoles(List.of(
                                    roleRepository.findByName(UserRole.USER)))
                            .setCart(new Cart().setCustomer(test3)));

            userRepository.saveAll(users);
        }
    }
}
