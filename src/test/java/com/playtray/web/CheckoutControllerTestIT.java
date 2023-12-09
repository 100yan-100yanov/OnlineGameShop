package com.playtray.web;

import com.playtray.model.entity.User;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckoutControllerTestIT {

    private final String TEST_USERNAME = "TestUser";

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
        testDataUtil.addItemToCart(user);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/checkout/finish")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("purchase-complete"));
    }
}
