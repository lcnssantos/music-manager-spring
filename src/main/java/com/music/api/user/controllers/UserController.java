package com.music.api.user.controllers;

import com.music.api.user.entity.User;
import com.music.api.user.errors.InvalidUserCredentialError;
import com.music.api.user.errors.UserNotFoundedError;
import com.music.api.user.requests.ChangePasswordRequest;
import com.music.api.user.requests.CreateUserRequest;
import com.music.api.user.requests.DeleteUserRequest;
import com.music.api.user.responses.UserResponse;
import com.music.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        if (!userRequest.password.equals(userRequest.passwordAgain)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            User user = this.userService.createAccount(userRequest);
            UserResponse response = new UserResponse(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordRequest passwordRequest) {
        try {
            this.userService.changePassword(passwordRequest.oldPassword, passwordRequest.newPassword, passwordRequest.email);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundedError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidUserCredentialError e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@Valid @RequestBody DeleteUserRequest deleteRequest) {
        try {
            this.userService.deleteAccount(deleteRequest.email, deleteRequest.password);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundedError e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidUserCredentialError e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/genre/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addGenreToUser(@PathVariable("id") String id, HttpServletRequest request) {
        try {
            User user = (User) request.getAttribute("user");
            this.userService.addGenre(user, Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
