package com.playtray.web;

import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.ProductRepository;
import com.playtray.utils.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTestIT {

    private static final String TEST_USERNAME = "TestUser";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestDataUtil testDataUtil;

    @AfterEach
    void tearDown() {
        testDataUtil.cleanUp();
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testShowItemsInCart() throws Exception {
        testDataUtil.createUser(TEST_USERNAME);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cart")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cart"));
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testAddItemToCart() throws Exception {
        Product product = productRepository.findAll().get(0);
        testDataUtil.createUser(TEST_USERNAME);

        mockMvc.perform(post("/cart/add/{id}", product.getId())
                        .with(csrf()))
                .andExpect(status().isFound())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testRemoveItemFromCart() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);
        testDataUtil.addItemToCart(user);
        Long productId = user.getCart().getItems().get(0).getProduct().getId();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cart/remove/{id}", productId)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
    }
}
