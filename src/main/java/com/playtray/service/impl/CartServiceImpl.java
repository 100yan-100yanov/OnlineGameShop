package com.playtray.service.impl;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.CartRepository;
import com.playtray.repository.ProductRepository;
import com.playtray.service.CartService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductRepository productRepository,
                           UserService userService) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void addToCart(Long productId, Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findCartByCustomer(customer);

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NullPointerException("Product with id " + productId + " doesn't exist!"));

        Item item = cart.getItem(product);

        if (item != null) {
            int quantity = item.getQuantity();
            item.setQuantity(quantity + 1);

        } else {
            item = new Item();

            item.setProduct(product)
                    .setQuantity(1)
                    .setPrice();
        }

        cart.getItems().add(item);
        cart.setTotalPrice();

        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long productId, Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findCartByCustomer(customer);

        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new NullPointerException("Product with id " + productId + " doesn't exist!"));

        Item item = cart.getItem(product);
        cart.getItems().remove(item);
        cart.setTotalPrice();

        cartRepository.save(cart);
    }
}
