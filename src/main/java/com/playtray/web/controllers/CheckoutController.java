package com.playtray.web.controllers;

import com.playtray.model.dto.CartBuyDTO;
import com.playtray.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/finish")
    public ModelAndView finish(Principal principal,
                               CartBuyDTO cartBuyDTO) {

        cartService.buy(principal, cartBuyDTO);

        return new ModelAndView("purchase-complete");
    }
}
