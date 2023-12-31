package com.playtray.service.impl;

import com.playtray.service.exception.ObjectNotFoundException;
import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.playtray.constants.ExceptionMessages.USER_USERNAME_NOT_FOUND;

public class PlaytrayUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public PlaytrayUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(PlaytrayUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException(USER_USERNAME_NOT_FOUND + username));
    }

    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles()
                        .stream()
                        .map(PlaytrayUserDetailsService::grantAuthority)
                        .toList())
                .build();
    }

    private static GrantedAuthority grantAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getName());
    }
}
