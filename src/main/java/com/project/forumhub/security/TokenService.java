package com.project.forumhub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.project.forumhub.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    private static final String ISSUER = "forumhub";

    private final Algorithm algorithm;
    private final long expirationSeconds;

    public TokenService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration:3600}") long expirationSeconds
    ) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("jwt.secret não configurado");
        }
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationSeconds = expirationSeconds;
    }

    public String generate(User user) {
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withClaim("uid", user.getId())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plusSeconds(expirationSeconds)))
                .sign(algorithm);
    }

    public String validateAndGetSubject(String token) {
        try {
            return verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public DecodedJWT verify(String token) {
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(stripBearer(token));
    }

    private static String stripBearer(String token) {
        return (token != null && token.startsWith("Bearer ")) ? token.substring(7) : token;
    }
}
