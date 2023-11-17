package com.playtray.web.controllers;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/consoles/all")
    public ModelAndView allConsoles() {
        return new ModelAndView("products-consoles");
    }
    @GetMapping("/consoles/{id}")
    public ModelAndView getConsole(@PathVariable("id") String id) {
        return new ModelAndView("products");
    }

    @GetMapping("/games/all")
    public ModelAndView allGames() {
        return new ModelAndView("products-games");
    }
    @GetMapping("/games/{id}")
    public ModelAndView getGame(@PathVariable("id") String id) {
        return new ModelAndView("products");
    }

    @GetMapping("/accessories/all")
    public ModelAndView allAccessories() {
        return new ModelAndView("products-accessories");
    }
    @GetMapping("/accessories/{id}")
    public ModelAndView getAccessory(@PathVariable("id") String id) {
        return new ModelAndView("products");
    }

    @PostMapping("/add")
    public ModelAndView add(ProductAddDTO productAddDTO) {
        this.productService.add(productAddDTO);

        return new ModelAndView("redirect:/products/add");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.productService.delete(id);

        return new ModelAndView("redirect:/products/delete");
    }
}
