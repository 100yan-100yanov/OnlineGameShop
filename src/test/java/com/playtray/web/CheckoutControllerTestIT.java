package com.playtray.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.playtray.model.dto.CartDTO;
import com.playtray.model.entity.Cart;
import com.playtray.model.entity.User;
import com.playtray.utils.TestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerTestIT {

    private static final String TEST_USERNAME = "TestUser";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @AfterEach
    void tearDown() {
        testDataUtil.cleanUp();
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testCheckoutWithItems() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);
        testDataUtil.addItemToCart(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/checkout")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andExpect(model().attributeExists("cart"));
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void techCheckoutWithoutItems() throws Exception {
        testDataUtil.createUser(TEST_USERNAME);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/checkout")
                        .with(csrf()))
                .andExpect(model().attributeExists("no_items"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart?no_items=true"));
    }

    @Test
    @WithMockUser(username = TEST_USERNAME)
    void testFinishOrderAtCheckout() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);
        Cart cart = user.getCart();

        testDataUtil.addItemToCart(user);
        CartDTO cartDTO = testDataUtil.createCartDTO(cart);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/checkout/finish")
                        .content(testDataUtil.asJsonString(cartDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/purchase-complete"));
    }
}