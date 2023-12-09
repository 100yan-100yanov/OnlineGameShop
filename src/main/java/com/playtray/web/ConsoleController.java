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
public class ConsoleController {

    private final ProductService productService;

    public ConsoleController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/consoles/all")
    public ModelAndView allConsoles(@PageableDefault(size = 3, sort = "id")
                                    Pageable pageable) {

        Page<ProductSummaryDTO> allConsoles =
                productService.getAll(ProductCategory.CONSOLE, pageable);

        ModelAndView modelAndView = new ModelAndView("consoles");
        modelAndView.addObject("consoles", allConsoles);

        return modelAndView;
    }

    @GetMapping("/consoles/{id}")
    public ModelAndView consoleDetails(@PathVariable("id") Long id) {

        ProductDetailsDTO consoleDetails = productService.getProductDetails(id);

        ModelAndView modelAndView = new ModelAndView("console-details");
        modelAndView.addObject("consoleDetails", consoleDetails);

        return modelAndView;
    }
}
