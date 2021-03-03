package com.music.api.user.controllers;

import com.music.api.user.entity.User;
import com.music.api.user.requests.AuthUserRequest;
import com.music.api.user.responses.AuthUserResponse;
import com.music.api.user.responses.UserResponse;
import com.music.api.user.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthUserResponse> login(@Valid @RequestBody AuthUserRequest request) {
        try {
            String jwt = this.authService.login(request.email, request.password);
            AuthUserResponse response = new AuthUserResponse(jwt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getAuthData(HttpServletRequest request) {
        try {
            User user = (User) request.getAttribute("user");
            UserResponse response = new UserResponse(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
