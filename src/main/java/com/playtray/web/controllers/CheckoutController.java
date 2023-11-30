package com.playtray.web.controllers;

import com.playtray.model.dto.CartDTO;
import com.playtray.service.CartService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final UserService userService;

    public CheckoutController(CartService cartService,
                              UserService userService) {

        this.cartService = cartService;
        this.userService = userService;
    }

    @PostMapping()
    public ModelAndView checkout(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("checkout");

        CartDTO cartDTO = cartService.getCart(principal);
        modelAndView.addObject("cart", cartDTO);

        return modelAndView;
    }
    @PostMapping("/finish")
    public ModelAndView finish(Principal principal,
                               CartDTO cartDTO) {

        cartService.buy(principal, cartDTO);

        return new ModelAndView("purchase-complete");
    }
}
