package com.playtray.web;

import com.playtray.model.entity.Product;
import com.playtray.repository.ProductRepository;
import com.playtray.utils.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testShowAllGames() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/games/all")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("games"))
                .andExpect(model().attributeExists("games"));
    }

    @Test
    void testShowGameDetails() throws Exception {
        Product product = productRepository.findAll().get(0);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/games/{id}", product.getId())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("game-details"))
                .andExpect(model().attributeExists("gameDetails"));
    }
}
