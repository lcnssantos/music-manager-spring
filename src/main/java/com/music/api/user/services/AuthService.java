package com.music.api.user.services;

import com.music.api.user.entity.User;
import com.music.api.user.errors.ExpiredJWTError;
import com.music.api.user.errors.InvalidUserCredentialError;
import com.music.api.user.errors.UserNotFoundedError;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private HashService hashService;

    public String login(String email, String password) throws InvalidUserCredentialError, UserNotFoundedError {
        User user = this.getUser(email, password);
        return this.jwtService.sign(user.getId().toString());
    }

    private User getUser(String email, String password) throws InvalidUserCredentialError, UserNotFoundedError {
        User user = this.userService.getByEmail(email);

        if(this.hashService.compare(user.getPassword(), password)) {
            return user;
        }

        throw new InvalidUserCredentialError("Invalid user");
    }

    private boolean isPasswordValid(String password, User user) {
        return BCrypt.checkpw(password, user.getPassword());
    }

    public User getData(String authorization) throws ExpiredJWTError, InvalidUserCredentialError {
        String jwt = authorization.replaceAll("Bearer ", "");
        Long id = this.jwtService.decode(jwt);
        Optional<User> userOptional = this.userService.getById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new InvalidUserCredentialError("Invalid user");
    }
}
