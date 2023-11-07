package com.playtray.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ModelAndView product(@PathVariable ("id") String id) {
        return new ModelAndView("products");
    }
}
