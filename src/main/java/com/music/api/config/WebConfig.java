package com.music.api.config;

import com.music.api.interceptors.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/auth/data")
                .addPathPatterns("/user/genre/**")
                .addPathPatterns("/singer/**")
                .addPathPatterns("/genre/**")
                .addPathPatterns("/music/**")
                .addPathPatterns("/avaliation/**")
                .addPathPatterns("/recomendation");

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
        registry.addMapping("/**/**");
    }
}
