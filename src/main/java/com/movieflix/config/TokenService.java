package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    @Value("${movieflix.security.issuer}")
    private String issuer;

    @Value("${movieflix.security.expiration-seconds}")
    private long expirationSeconds;

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(expirationSeconds))
                .sign(Algorithm.HMAC256(secret));
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decoded.getClaim("userId").asLong())
                    .name(decoded.getClaim("name").asString())
                    .email(decoded.getSubject())
                    .build());
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
