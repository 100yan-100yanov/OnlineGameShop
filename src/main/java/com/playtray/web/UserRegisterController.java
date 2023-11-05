package com.playtray.web;

import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserRegisterController {

    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterDTO")
                                 UserRegisterDTO userRegisterDTO) {

        if (userService.isUserLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid
                                 @ModelAttribute("userRegisterDTO")
                                 UserRegisterDTO userRegisterDTO,
                                 BindingResult bindingResult) {

        if (userService.isUserLogged()) {
            return new ModelAndView("redirect:/");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        boolean registrationSuccessful = this.userService.register(userRegisterDTO);

        if (!registrationSuccessful) {
            return new ModelAndView("register");
        }

        return new ModelAndView("redirect:/login");
    }
}