package com.playtray.web;

import com.playtray.model.dto.RoleDTO;
import com.playtray.model.dto.UserDTO;
import com.playtray.model.enums.UserRole;
import com.playtray.service.RoleService;
import com.playtray.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService,
                           RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView("manage-users");

        List<UserDTO> users = userService.getAllUsers();

        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @DeleteMapping("/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {

        userService.delete(id);

        return new ModelAndView("redirect:/admin/users");
    }

    @GetMapping("/users/{username}/roles")
    public ModelAndView userRoles(@PathVariable("username") String username) {

        List<RoleDTO> roles = roleService.getRolesByUsername(username);
        List<RoleDTO> unassignedRoles = roleService.getUnassignedRoles(username);

        ModelAndView modelAndView = new ModelAndView("manage-user-roles");
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("unassignedRoles", unassignedRoles);
        modelAndView.addObject("username", username);

        return modelAndView;
    }

    @PostMapping("/users/{username}/roles/remove/{roleId}")
    public ModelAndView removeUserRole(@PathVariable("username") String username,
                                       @PathVariable("roleId") Long roleId) {

        userService.removeUserRole(username, roleId);

        return new ModelAndView("redirect:/admin/users/{username}/roles");
    }

    @PostMapping("/users/{username}/roles/add/{roleName}")
    public ModelAndView addUserRole(@PathVariable("username") String username,
                                    @PathVariable("roleName") UserRole roleName) {

        userService.addUserRole(username, roleName);

        return new ModelAndView("redirect:/admin/users/{username}/roles");
    }
}
