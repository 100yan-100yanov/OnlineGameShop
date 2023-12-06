package com.playtray.utils;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.CartRepository;
import com.playtray.repository.ItemRepository;
import com.playtray.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CartTestDataUtil {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product getProduct() {
        return productRepository.findAll().get(0);
    }

    public Cart createCart(User customer) {

        return cartRepository.findCartByCustomer(customer);
    }

    public void addItem(Product product, Cart cart) {

        Item item = new Item()
                .setProduct(product)
                .setQuantity(1);

        cart.setItems(List.of(item));
    }

    public void cleanUp() {
        itemRepository.deleteAll();
        cartRepository.deleteAll();
    }
}
