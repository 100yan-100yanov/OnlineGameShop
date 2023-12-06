package com.playtray.web;

import com.playtray.model.entity.User;
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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @AfterEach
    void tearDown() {
        userTestDataUtil.cleanUp();
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN"})
    void testShowAllUsersToAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/admin/users")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-users"));
    }

    @Test
    @WithMockUser(username = "Admin", roles = {"ADMIN"})
    void testDeleteUser() throws Exception {
        User user = userTestDataUtil.createUser("TestUser");

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/users/delete/{id}", user.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users"));
    }
}
