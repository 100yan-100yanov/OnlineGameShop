package com.playtray.web.controllers.user;

import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserDeleteController {

    private final UserService userService;

    public UserDeleteController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/users/{id}")
    public ModelAndView delete(@PathVariable("id") Long id,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        //TODO

        userService.delete(id);

        return new ModelAndView("redirect:/users/delete");
    }
}
