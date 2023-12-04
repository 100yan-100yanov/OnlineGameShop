package com.playtray.utils;

import com.playtray.model.entity.Product;
import com.playtray.model.entity.Role;
import com.playtray.model.enums.PlatformName;
import com.playtray.model.enums.ProductCategory;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

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

        Product product = new Product()
                .setCategory(ProductCategory.CONSOLE)
                .setPlatform(PlatformName.PS5)
                .setName("Sony")
                .setPrice(BigDecimal.valueOf(1000.0))
                .setSummary("summary")
                .setDescription("Last generation game console by Sony")
                .setImageUrl("https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/p/l/d5cbabf70bde6f305d9d3ba3f76ae93f/playstation-5-standard-edition-31.jpg");

        productRepository.save(product);
    }
}

