package com.playtray.web.controllers;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid
                              @ModelAttribute("userLoginDTO")
                              UserLoginDTO userLoginDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult)
                    .addFlashAttribute("userLoginDTO", userLoginDTO);

            modelAndView.setViewName("redirect:/users/login");
        } else {
            userService.login(userLoginDTO);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @PostMapping("/login-error")
    public ModelAndView onFailure(
            @ModelAttribute("username") String username) {

        ModelAndView model = new ModelAndView("login");
        model.addObject("username", username);
        model.addObject("bad_credentials", true);

        return model;
    }

    @PostMapping("/logout")
    public ModelAndView logout() {

        userService.logout();

        return new ModelAndView("redirect:/");
    }
}