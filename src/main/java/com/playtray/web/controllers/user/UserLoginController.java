package com.playtray.web.controllers.user;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private final UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login(Model model,
                              @RequestParam(required = false) String error) {

        if (error != null) {
            model.addAttribute("error", error);
        }

        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid
                              @ModelAttribute("userLoginDTO")
                              UserLoginDTO userLoginDTO) {

        ModelAndView modelAndView = new ModelAndView();

        if (userService.login(userLoginDTO)) {
            modelAndView.setViewName("redirect:/");

        } else {
            modelAndView.addObject("bad_credentials", true);
            modelAndView.setViewName("redirect:/users/login");
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
    public ModelAndView logout(Principal principal) {

        userService.logout(principal);

        return new ModelAndView("redirect:/");
    }
}
