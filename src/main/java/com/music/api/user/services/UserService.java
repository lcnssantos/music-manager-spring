package com.music.api.user.services;

import com.music.api.genres.entity.Genre;
import com.music.api.genres.errors.GenreNotFoundedError;
import com.music.api.genres.services.GenreService;
import com.music.api.user.entity.User;
import com.music.api.user.errors.InvalidUserCredentialError;
import com.music.api.user.errors.UserNotFoundedError;
import com.music.api.user.repository.UserRepository;
import com.music.api.user.requests.CreateUserRequest;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HashService hashService;
    @Autowired
    private GenreService genreService;

    public User createAccount(CreateUserRequest userRequest) {
        String hashedPassword = this.hashService.hash(userRequest.password);

        User user = new User();

        user.setName(userRequest.name);
        user.setPassword(hashedPassword);
        user.setEmail(userRequest.email);

        return this.userRepository.save(user);
    }

    public User getByEmail(String email) throws UserNotFoundedError {
        Optional<User> user = this.userRepository.findByEmail(email);

        if(user.isPresent()) {
            return user.get();
        }

        throw new UserNotFoundedError("User email not founded");
    }

    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }

    public User changePassword(String oldPassword, String newPassword, String email) throws UserNotFoundedError, InvalidUserCredentialError {
        User user = this.getByEmail(email);

        if(this.hashService.compare(user.getPassword(), oldPassword)) {
            user.setPassword(this.hashService.hash(newPassword));
            return this.userRepository.save(user);
        }

        throw new InvalidUserCredentialError("Invalid credentials!");
    }

    public void deleteAccount(String email, String password) throws InvalidUserCredentialError, UserNotFoundedError {
        User user = this.getByEmail(email);

        if(this.hashService.compare(user.getPassword(), password)) {
            this.userRepository.delete(user);
            return;
        }

        throw new InvalidUserCredentialError("Invalid credentials!");
    }

    public void addGenre(User user, long id) throws GenreNotFoundedError {
        Genre genre = this.genreService.getById(id);
        user.addGenre(genre);
        this.userRepository.save(user);
    }
}
