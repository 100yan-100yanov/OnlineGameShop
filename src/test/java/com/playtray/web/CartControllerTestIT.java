package com.playtray.web;

import com.playtray.model.entity.Item;
import com.playtray.utils.ItemTestDataUtil;
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
public class CartControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemTestDataUtil itemTestDataUtil;

    @AfterEach
    void tearDown() {
        itemTestDataUtil.cleanUp();
    }

    @Test
    void testAddItemToCart() throws Exception {
        Item item = itemTestDataUtil.createItem();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cart/add/{id}", item.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/users/login"));
    }
}
