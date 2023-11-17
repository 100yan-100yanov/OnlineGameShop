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
        if (roleRepository.count() <= 0) {
            List<Role> roles = new ArrayList<>();

            Arrays.stream(UserRole.values())
                    .forEach(userRole -> {
                        Role role = new Role().setName(userRole);
                        roles.add(role);
                    });

            roleRepository.saveAll(roles);
        }

        Optional<User> admin = userRepository.findByUsername("admin");

        if (admin.isEmpty()) {
            User user = new User();

            user
                    .setFirstName("Stoyan")
                    .setLastName("Stoyanov")
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("666666"))
                    .setEmail("admin@playtray.com")
                    .setActive(true)
                    .setRoles(List.of(
                            roleRepository.findByName(UserRole.ADMIN),
                            roleRepository.findByName(UserRole.USER)))
                    .setCart(new Cart().setCustomer(user));

            userRepository.save(user);
        }
    }
}
