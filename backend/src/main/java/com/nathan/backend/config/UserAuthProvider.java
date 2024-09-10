package com.nathan.backend.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nathan.backend.entities.User;
import com.nathan.backend.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthProvider {

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

    @Value("${security.jwt.expiration}")
    private long EXPIRATION;

    private final UserService userService;

    public UserAuthProvider(UserService userService) {
        this.userService = userService;
    }

    public String createToken(String username) {
        Date now = new Date();

        return JWT.create()
                .withIssuer(username)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        User user = userService.findByUsername(decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
