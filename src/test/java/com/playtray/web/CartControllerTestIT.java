package com.playtray.web;

import com.playtray.model.entity.Cart;
import com.playtray.model.entity.Item;
import com.playtray.model.entity.Product;
import com.playtray.model.entity.User;
import com.playtray.repository.ProductRepository;
import com.playtray.service.CartService;
import com.playtray.utils.CartTestDataUtil;
import com.playtray.utils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartTestDataUtil cartTestDataUtil;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

//    @BeforeEach
//    void setUp() {
//        cartTestDataUtil.getCart(userTestDataUtil.createUser());
//    }

    @AfterEach
    void tearDown() {
        cartTestDataUtil.cleanUp();
        userTestDataUtil.cleanUp();
    }

    @Test
    void testAddItemToCart() throws Exception {
        Product product = cartTestDataUtil.getProduct();

        mockMvc.perform(post("/cart/add/{id}", product.getId())
                        .with(csrf()))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser(username = "TestUser")
    void testRemoveItemFromCart() throws Exception {
        User user = userTestDataUtil.createUser("TestUser");
        Product product = productRepository.findAll().get(0);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cart/remove/{id}", product.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));
        // TODO fix
    }
}
