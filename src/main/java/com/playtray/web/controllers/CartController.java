package com.playtray.web.controllers;

import com.playtray.model.dto.CartDTO;
import com.playtray.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView cart(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("cart");

        CartDTO cartDTO = cartService.getCart(principal);
        modelAndView.addObject("cart", cartDTO);

        return modelAndView;
    }

    @RequestMapping("/add/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long id,
                                  Principal principal,
                                  HttpServletRequest httpRequest) {

        cartService.addToCart(id, principal);
        String referer = httpRequest.getHeader("Referer");

        return new ModelAndView("redirect:" + referer);
    }

    @RequestMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable("id") Long productId,
                                       Principal principal) {

        cartService.removeFromCart(productId, principal);

        return new ModelAndView("redirect:/cart");
    }
}
