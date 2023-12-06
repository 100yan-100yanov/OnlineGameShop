package com.playtray.web;

import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.RoleRepository;
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
@WithMockUser(username = "Admin", roles = {"ADMIN"})
public class AdminControllerTestIT {

    private static final String TEST_USERNAME = "TestUser";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TestDataUtil testDataUtil;

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        testDataUtil.cleanUp();
    }

    @Test
    void testShowAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/users")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-users"));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/users/delete/{id}", user.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users"));
    }

    @Test
    void testShowUserRoles() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/users/{username}/roles", user.getUsername())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-user-roles"));
    }

    @Test
    void testAddUserRoleToUser() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);
        Role role = roleRepository.findByName(UserRole.ADMIN);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/users/{username}/roles/add/{roleId}",
                                user.getUsername(),
                                role.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users/" + TEST_USERNAME + "/roles"));
    }

    @Test
    void testRemoveUserRoleFromUser() throws Exception {
        User user = testDataUtil.createUser(TEST_USERNAME);
        Role role = roleRepository.findByName(UserRole.USER);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/admin/users/{username}/roles/remove/{roleId}",
                                user.getUsername(),
                                role.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/users/" + TEST_USERNAME + "/roles"));
    }
}
