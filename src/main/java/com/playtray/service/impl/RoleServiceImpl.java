package com.playtray.service.impl;

import com.playtray.model.dto.RoleDTO;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.repository.RoleRepository;
import com.playtray.repository.UserRepository;
import com.playtray.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleDTO> getRolesByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist!"));

        List<Role> roles = user.getRoles();

        return roles
                .stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .toList();
    }

    @Override
    public List<RoleDTO> getUnassignedRoles(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist!"));

        List<Role> allRoles = roleRepository.findAll();
        List<Role> userRoles = user.getRoles();

        for (Role userRole : userRoles) {
            allRoles.removeIf(role -> userRole.getId().equals(role.getId()));
        }

        return allRoles
                .stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .toList();
    }
}
