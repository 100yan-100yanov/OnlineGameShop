package com.playtray.service;

import com.playtray.model.dto.CartDTO;

import java.security.Principal;

public interface CartService {

    void addToCart(Long id, Principal principal);

    void removeFromCart(Long cartId, Principal principal);

    void buy(Principal principal);

    CartDTO getCart(Principal principal);

    void clearCart(Principal principal);
}
