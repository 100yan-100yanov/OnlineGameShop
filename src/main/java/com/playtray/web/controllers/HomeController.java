package com.playtray.web.controllers;

import com.playtray.model.dto.ProductDTO;
import com.playtray.model.enums.ProductCategory;
import com.playtray.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        ProductDTO gameDTO = productService.findLatest(ProductCategory.GAME);
        ProductDTO consoleDTO = productService.findLatest(ProductCategory.CONSOLE);
        ProductDTO accessoryDTO = productService.findLatest(ProductCategory.ACCESSORY);

        modelAndView.addObject("game", gameDTO);
        modelAndView.addObject("console", consoleDTO);
        modelAndView.addObject("accessory", accessoryDTO);

        return modelAndView;
    }

}
