package com.playtray.web.controllers;

import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ModelAndView allUsers() {
        return new ModelAndView("users");
    }
    @DeleteMapping("/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {

        userService.delete(id);

        return new ModelAndView("redirect:/admin/users");
    }
}
