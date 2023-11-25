package com.playtray.service;

import java.security.Principal;

public interface CartService {

    void addToCart(Long id, Principal principal);

    void removeFromCart(Long cartId, Principal principal);
}
