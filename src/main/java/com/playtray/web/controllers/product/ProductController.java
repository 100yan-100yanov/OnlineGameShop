package com.playtray.web.controllers.product;

import com.playtray.model.dto.ProductAddDTO;
import com.playtray.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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
