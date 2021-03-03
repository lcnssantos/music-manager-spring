package com.music.api.interceptors;


import com.music.api.user.entity.User;
import com.music.api.user.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String authorization = request.getHeader("Authorization");
            User user = this.authService.getData(authorization);
            request.setAttribute("user", user);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
