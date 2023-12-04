package com.playtray.service.impl;

import com.playtray.model.entity.Role;
import com.playtray.model.entity.User;
import com.playtray.model.enums.UserRole;
import com.playtray.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlaytrayUserDetailsServiceTest {

    private PlaytrayUserDetailsService serviceTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceTest = new PlaytrayUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceTest.loadUserByUsername("Unknown"));
    }

    @Test
    void testUserFoundException() {
        User testUser = createTestUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = serviceTest.loadUserByUsername(testUser.getUsername());

        assertNotNull(userDetails);

        assertEquals(testUser.getUsername(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());

        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRole.USER),
                "The user is not USER");

        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRole.ADMIN),
                "The user is not ADMIN");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private User createTestUser() {
        return new User()
                .setUsername("username")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("email@email.com")
                .setPassword("password")
                .setActive(false)
                .setRoles(List.of(
                        new Role().setName(UserRole.ADMIN),
                        new Role().setName(UserRole.USER)
                ));
    }
}
