package com.playtray.repository;

import com.playtray.model.entity.Role;
import com.playtray.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(UserRole userRole);
}
