package com.playtray.service.impl;

import com.playtray.model.dto.CartBuyDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.CartRepository;
import com.playtray.service.CartService;
import com.playtray.service.ProductService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService,
                           UserService userService) {

        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    public void addToCart(Long productId, Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = cartRepository.findCartByCustomer(customer);

        Product product = productService.findById(productId);

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

        Product product = productService.findById(productId);

        Item item = cart.getItem(product);
        cart.getItems().remove(item);
        cart.setTotalPrice();

        cartRepository.save(cart);
    }

    @Override
    public void buy(Principal principal, CartBuyDTO cartBuyDTO) {
        User customer = userService.findByUsername(principal.getName());

        List<Product> products = cartBuyDTO.items()
                .stream()
                .map(Item::getProduct)
                .toList();

        customer.setBoughtProducts(products);

        userService.save(customer);
    }
}
