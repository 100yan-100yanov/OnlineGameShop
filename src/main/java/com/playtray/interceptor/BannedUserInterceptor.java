package com.playtray.interceptor;

import com.playtray.error.ObjectNotFoundException;
import com.playtray.model.entity.User;
import com.playtray.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;

import static com.playtray.constants.ExceptionMessages.USER_USERNAME_NOT_FOUND;


@Component
public class BannedUserInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(BannedUserInterceptor.class);

    public BannedUserInterceptor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            String username = request.getUserPrincipal().getName();
            User user = userRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new ObjectNotFoundException(USER_USERNAME_NOT_FOUND));

            if (user.getRoles().size() == 0) {
                logger.info("Banned user tried to login! Username: {}", user.getUsername());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                return false;
            }
        }
        return true;
    }
}
