package com.playtray.utils;

import com.playtray.model.entity.*;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username) {
        List<Role> roles = List.of(roleRepository.findByName(UserRole.USER));
        User user = new User();
        Cart cart = new Cart();

        cart
                .setCustomer(user)
                .setItems(new ArrayList<>())
                .setTotalPrice();

        user
                .setUsername(username)
                .setPassword(passwordEncoder.encode("TestPass"))
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("mail@email.com")
                .setCart(cart)
                .setActive(true)
                .setRoles(roles)
                .setBoughtProducts(new ArrayList<>());

        userRepository.save(user);
        cartRepository.save(cart);

        return user;
    }

    public Item createItem(Product product) {
        Item item = new Item();

        item
                .setProduct(product)
                .setQuantity(1)
                .setPrice();

        return itemRepository.save(item);
    }

    public void addItemToCart(User customer) {
        Product product = productRepository.findAll().get(0);
        Item item = createItem(product);

        customer.getCart().setItems(List.of(item));

        userRepository.save(customer);
    }

    public void cleanUp() {
        userRepository.deleteAll();
        cartRepository.deleteAll();
        itemRepository.deleteAll();
    }
}
