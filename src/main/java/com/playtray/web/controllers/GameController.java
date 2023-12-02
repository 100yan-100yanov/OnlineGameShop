package com.playtray.web.controllers;

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
public class GameController {

    private final ProductService productService;

    public GameController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/games/all")
    public ModelAndView allGames(@PageableDefault(size = 3, sort = "id")
                                 Pageable pageable) {

        Page<ProductSummaryDTO> allGames =
                productService.getAll(ProductCategory.GAME, pageable);

        ModelAndView modelAndView = new ModelAndView("games");
        modelAndView.addObject("games", allGames);

        return modelAndView;
    }

    @GetMapping("/games/{id}")
    public ModelAndView gameDetails(@PathVariable("id") Long id) {

        ProductDetailsDTO gameDetails = productService
                .getProductDetails(id)
                .orElseThrow(() -> new NullPointerException("Game with id " + id + " not found!"));

        ModelAndView modelAndView = new ModelAndView("game-details");
        modelAndView.addObject("gameDetails", gameDetails);

        return modelAndView;
    }
}
