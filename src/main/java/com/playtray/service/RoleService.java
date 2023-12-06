package com.playtray.service;

import com.playtray.model.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getRolesByUsername(String username);

    List<RoleDTO> getUnassignedRoles(String username);
}
