package com.playtray.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserLoginController {


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login-error")
    public ModelAndView onFailure() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("bad_credentials", true);

        return modelAndView;
    }
}
