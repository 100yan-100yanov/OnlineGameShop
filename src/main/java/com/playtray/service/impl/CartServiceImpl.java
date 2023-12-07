package com.playtray.service.impl;

import com.playtray.model.dto.CartDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.CartRepository;
import com.playtray.service.CartService;
import com.playtray.service.ItemService;
import com.playtray.service.ProductService;
import com.playtray.service.UserService;
import com.playtray.error.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ItemService itemService;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository,
                           ProductService productService,
                           UserService userService,
                           ItemService itemService,
                           ModelMapper modelMapper) {

        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
        this.itemService = itemService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addToCart(Long productId, Principal principal) {
        Product product = productService.findById(productId);
        User customer = userService.findByUsername(principal.getName());
        Cart cart = customer.getCart();
        Item item = getItemFromCart(productId, cart);

        if (item != null) {
            int itemQuantity = item.getQuantity();

            item
                    .setQuantity(itemQuantity + 1)
                    .setPrice();

        } else {
            item = new Item();

            item
                    .setProduct(product)
                    .setQuantity(1)
                    .setPrice();

            cart.getItems().add(item);
        }

        cart.setTotalPrice();
        cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long productId, Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = customer.getCart();
        Item item = getItemFromCart(productId, cart);

        if (item != null) {
            cart.getItems().remove(item);
            cart.setTotalPrice();

            cartRepository.save(cart);
            itemService.delete(item);

        } else {
            throw new ObjectNotFoundException("Product with id " + productId + " doesn't exist!");
        }

    }

    @Override
    public void buy(Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = customer.getCart();

        List<Product> products = cart.getItems()
                .stream()
                .map(Item::getProduct)
                .toList();

        customer.getBoughtProducts().addAll(products);

        userService.save(customer);
    }

    @Override
    public CartDTO getCart(Principal principal) {
        User customer = userService.findByUsername(principal.getName());
        Cart cart = customer.getCart();

        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public void clearCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Cart cart = user.getCart();

        List<Item> items = cart.getItems();

        cart.setItems(new ArrayList<>())
                .setTotalPrice();

        cartRepository.save(cart);
        items.forEach(itemService::delete);
    }

    private Item getItemFromCart(Long productId, Cart cart) {
        for (Item cartItem : cart.getItems()) {
            Long cartProductId = cartItem.getProduct().getId();

            if (cartProductId.equals(productId)) {
                return cartItem;
            }
        }
        return null;
    }
}
