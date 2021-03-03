package com.music.api.user.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.music.api.user.errors.ExpiredJWTError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService<T> {

    @Value("${jwt.key}")
    private String key;

    public String sign(String id) {
        Algorithm algorithm = Algorithm.HMAC256(this.key);
        return JWT.create()
                .withSubject(id)
                .withExpiresAt(new Date(new Date().getTime() + 24 * 3600 * 1000))
                .sign(algorithm);
    }

    public Long decode(String jwt) throws ExpiredJWTError {
        DecodedJWT data = JWT.decode(jwt);

        if (data.getExpiresAt().getTime() > new Date().getTime()) {
            return Long.parseLong(JWT.decode(jwt).getSubject());
        }

        throw new ExpiredJWTError(String.format("Token expired at %s", data.getExpiresAt().toString()));
    }
}
