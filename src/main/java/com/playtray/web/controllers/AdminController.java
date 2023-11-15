package com.playtray.web.controllers;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.dto.UserDeleteDTO;
import com.playtray.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users/delete")
    public ModelAndView deleteUser() {
        return new ModelAndView("user-delete");
    }

    @PostMapping("/users/delete")
    public ModelAndView deleteUser(UserDeleteDTO userDeleteDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        //TODO

        adminService.deleteUser(userDeleteDTO);

        return new ModelAndView("redirect:/delete-user");
    }

    @GetMapping("/products/add")
    public ModelAndView addProduct() {
        return new ModelAndView("product-add");
    }

    @PostMapping("/products/add")
    public ModelAndView addProduct(ProductAddDTO productAddDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        //TODO

        adminService.addProduct(productAddDTO);

        return new ModelAndView("redirect:/product-add");
    }

    @GetMapping("/products/delete")
    public ModelAndView deleteProduct() {
        return new ModelAndView("product-delete");
    }

    @PostMapping("/products/delete")
    public ModelAndView deleteProduct(ProductAddDTO productAddDTO,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {

        //TODO

        adminService.deleteProduct(productAddDTO);

        return new ModelAndView("redirect:/product-delete");
    }
}
