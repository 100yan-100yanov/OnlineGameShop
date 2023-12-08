package com.playtray.config;

import com.playtray.interceptor.BannedUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final BannedUserInterceptor bannedUserInterceptor;

    public WebConfig(BannedUserInterceptor bannedUserInterceptor) {
        this.bannedUserInterceptor = bannedUserInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bannedUserInterceptor);
    }
}
