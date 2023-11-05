package com.playtray.web;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.service.UserService;
import com.playtray.service.session.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserLoginController(UserService userService,
                               LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("userLoginDTO")
                              UserLoginDTO userLoginDTO) {

        if (userService.isUserLogged()) {
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid
                              @ModelAttribute("userLoginDTO")
                              UserLoginDTO userLoginDTO,
                              BindingResult bindingResult) {

        if (userService.isUserLogged()) {
            return new ModelAndView("redirect:/");
        }

        boolean loginSuccessful = userService.login(userLoginDTO);

        if (!loginSuccessful) {
            ModelAndView model = new ModelAndView("login");
            model.addObject("loginError", true);

            return model;
        }

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {

        if (userService.isUserLogged()) {
            return new ModelAndView("redirect:/");
        }

        userService.logout();

        return new ModelAndView("redirect:/");
    }
}
