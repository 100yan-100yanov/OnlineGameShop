package com.playtray.web.controllers;

import com.playtray.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ModelAndView cart() {
        return new ModelAndView("cart");
    }

    @PostMapping("/add")
    public ModelAndView addToCart(Long productId,
                                  Principal principal,
                                  HttpServletRequest httpRequest) {

        cartService.addToCart(productId, principal);
        String referer = httpRequest.getHeader("Referer");

        return new ModelAndView("redirect:" + referer);
    }

    @PostMapping("/remove")
    public ModelAndView removeFromCart(Long productId,
                                       Principal principal,
                                       HttpServletRequest httpRequest) {

        cartService.removeFromCart(productId, principal);
        String referer = httpRequest.getHeader("Referer");

        return new ModelAndView("redirect:" + referer);
    }
}
