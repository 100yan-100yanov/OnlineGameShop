package com.playtray.web;

import com.playtray.model.dto.UserLoginDTO;
import com.playtray.service.AuthenticationService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserLoginController(UserService userService,
                               AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDTO userLoginDTO) {

        authenticationService.login(userLoginDTO);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:/");
    }
}
