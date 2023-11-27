package com.playtray.service;

import com.playtray.model.dto.CartBuyDTO;

import java.security.Principal;

public interface CartService {

    void addToCart(Long id, Principal principal);

    void removeFromCart(Long cartId, Principal principal);

    void buy(Principal principal, CartBuyDTO cartBuyDTO);
}
