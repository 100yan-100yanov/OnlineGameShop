package com.playtray.config;

import com.playtray.model.enums.UserRole;
import com.playtray.repository.UserRepository;
import com.playtray.service.impl.PlaytrayUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    //TODO
//    private final String rememberMeKey;
//
//    public SecurityConfiguration(@Value("${playtray.remember.me.key}")
//                                 String rememberMeKey) {
//        this.rememberMeKey = rememberMeKey;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                request -> request
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/products/add", "/products/delete", "/users/delete").hasRole(UserRole.ADMIN.name())
                        .requestMatchers("/cart", "/cart/**").hasRole(UserRole.USER.name())
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
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
//        ).rememberMe(
//                rememberMe -> rememberMe
//                        .key(rememberMeKey)
//                        .rememberMeParameter("rememberme")
//                        .rememberMeCookieName("rememberme")
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
