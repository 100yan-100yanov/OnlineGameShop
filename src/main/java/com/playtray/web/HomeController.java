package com.playtray.web;

import com.playtray.model.dto.ProductLatestDTO;
import com.playtray.model.enums.ProductCategory;
import com.playtray.service.ProductService;
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

        ProductLatestDTO gameDTO = productService.findLatest(ProductCategory.GAME);
        ProductLatestDTO consoleDTO = productService.findLatest(ProductCategory.CONSOLE);
        ProductLatestDTO accessoryDTO = productService.findLatest(ProductCategory.ACCESSORY);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("game", gameDTO);
        modelAndView.addObject("console", consoleDTO);
        modelAndView.addObject("accessory", accessoryDTO);

        return modelAndView;
    }

}
