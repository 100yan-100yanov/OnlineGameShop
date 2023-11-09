package com.playtray.web;

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

    @GetMapping("/{id}")
    public ModelAndView product(@PathVariable("id") String id) {
        return new ModelAndView("products");
    }

    @PostMapping("/add")
    public ModelAndView add(ProductAddDTO productAddDTO) {
        this.productService.add(productAddDTO);

        return new ModelAndView("redirect:/home");
    }

    @DeleteMapping("/delete")
    public ModelAndView delete(Long id) {
        this.productService.delete(id);

        return new ModelAndView("redirect:/home");
    }
}
