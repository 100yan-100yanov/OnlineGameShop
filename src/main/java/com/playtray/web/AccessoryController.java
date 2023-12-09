package com.playtray.web;

import com.playtray.model.dto.ProductDetailsDTO;
import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.enums.ProductCategory;
import com.playtray.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class AccessoryController {

    private final ProductService productService;

    public AccessoryController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/accessories/all")
    public ModelAndView allAccessories(@PageableDefault(size = 3, sort = "id")
                                       Pageable pageable) {

        Page<ProductSummaryDTO> allAccessories =
                productService.getAll(ProductCategory.ACCESSORY, pageable);

        ModelAndView modelAndView = new ModelAndView("accessories");
        modelAndView.addObject("accessories", allAccessories);

        return modelAndView;
    }

    @GetMapping("/accessories/{id}")
    public ModelAndView accessoryDetails(@PathVariable("id") Long id) {

        ProductDetailsDTO accessoryDetails = productService.getProductDetails(id);

        ModelAndView modelAndView = new ModelAndView("accessory-details");
        modelAndView.addObject("accessoryDetails", accessoryDetails);

        return modelAndView;
    }
}
