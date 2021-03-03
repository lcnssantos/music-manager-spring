package com.music.api.user.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public Boolean compare(String hashPassword, String password) {
        return BCrypt.checkpw(password, hashPassword);
    }

}
