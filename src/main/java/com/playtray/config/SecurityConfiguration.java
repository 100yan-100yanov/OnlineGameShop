package com.playtray.config;

import com.playtray.model.enums.UserRole;
import com.playtray.repository.UserRepository;
import com.playtray.service.impl.PlaytrayUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/", "/about-us").permitAll()
                                .requestMatchers("/users/login-error").permitAll()
                                .requestMatchers("/users/register", "/users/login").anonymous()
                                .requestMatchers("/users/logout").hasRole(UserRole.USER.name())
                                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
                                .requestMatchers("/cart", "/cart/add/**", "/cart/remove/**").hasRole(UserRole.USER.name())
                                .requestMatchers("/checkout", "checkout/finish").hasRole(UserRole.USER.name())
                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(
                        login -> login
                                .loginPage("/users/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/users/login-error")
                ).logout(
                        logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)

                ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new PlaytrayUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
