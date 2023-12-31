package com.playtray.init;

import com.playtray.model.entity.Role;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RolesInit implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RolesInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
    }
}
