package com.playtray.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    private String username;

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .param("username", "Username")
                        .param("firstName", "User")
                        .param("lastName", "Userov")
                        .param("email", "user@email.com")
                        .param("password", "123456")
                        .param("confirmPassword", "123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }
}
