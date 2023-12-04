package com.playtray.web;

import com.playtray.model.dto.ProductDetailsDTO;
import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.enums.ProductCategory;
import com.playtray.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView allConsoles(Model model,
                                    @PageableDefault(size = 3, sort = "id")
                                    Pageable pageable) {

        Page<ProductSummaryDTO> allConsoles =
                productService.getAll(ProductCategory.CONSOLE, pageable);

        model.addAttribute("consoles", allConsoles);

        return new ModelAndView("consoles");
    }

    @GetMapping("/consoles/{id}")
    public ModelAndView consoleDetails(Model model,
                                       @PathVariable("id") Long id) {

        ProductDetailsDTO consoleDetails = productService
                .getProductDetails(id)
                .orElseThrow(() -> new NullPointerException("Console with id " + id + " not found!"));

        model.addAttribute("consoleDetails", consoleDetails);

        return new ModelAndView("console-details");
    }
}
