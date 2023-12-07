package com.playtray.web;

import com.playtray.model.dto.CartDTO;
import com.playtray.service.CartService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;

    public CheckoutController(CartService cartService) {

        this.cartService = cartService;
    }

    @GetMapping()
    public ModelAndView checkout(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        CartDTO cartDTO = cartService.getCart(principal);

        if (cartDTO.getItems().size() == 0) {
            modelAndView.addObject("no_items", true);
            modelAndView.setViewName("redirect:/cart");

        } else {
            modelAndView.addObject("cart", cartDTO);
            modelAndView.setViewName("checkout");
        }

        return modelAndView;
    }

    @PostMapping("/finish")
    public ModelAndView finish(Principal principal) {

        cartService.buy(principal);
        cartService.clearCart(principal);

        return new ModelAndView("purchase-complete");
    }
}
