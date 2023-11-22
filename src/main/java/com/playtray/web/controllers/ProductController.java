package com.playtray.web.controllers;

import com.playtray.model.dto.ProductSummaryDTO;
import com.playtray.model.dto.ProductAddDTO;
import com.playtray.model.enums.ProductCategory;
import com.playtray.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView allConsoles(Model model,
                                    @PageableDefault(size = 3, sort = "id")
                                    Pageable pageable) {

        Page<ProductSummaryDTO> allConsoles =
                productService.getAll(ProductCategory.CONSOLE, pageable);

        model.addAttribute("consoles", allConsoles);

        return new ModelAndView("consoles");
    }

    @GetMapping("/consoles/{id}")
    public ModelAndView getConsole(@PathVariable("id") String id) {
        return new ModelAndView("consoles");
    }

    @GetMapping("/games/all")
    public ModelAndView allGames(Model model,
                                 @PageableDefault(size = 3, sort = "id")
                                 Pageable pageable) {

        Page<ProductSummaryDTO> allGames =
                productService.getAll(ProductCategory.GAME, pageable);

        model.addAttribute("games", allGames);

        return new ModelAndView("games");
    }

    @GetMapping("/games/{id}")
    public ModelAndView getGame(@PathVariable("id") String id) {
        return new ModelAndView("consoles");
    }

    @GetMapping("/accessories/all")
    public ModelAndView allAccessories(Model model,
                                       @PageableDefault(size = 3, sort = "id")
                                       Pageable pageable) {

        Page<ProductSummaryDTO> allAccessories =
                productService.getAll(ProductCategory.ACCESSORY, pageable);

        model.addAttribute("accessories", allAccessories);

        return new ModelAndView("accessories");
    }

    @GetMapping("/accessories/{id}")
    public ModelAndView getAccessory(@PathVariable("id") String id) {
        return new ModelAndView("consoles");
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
