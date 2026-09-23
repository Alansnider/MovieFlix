package com.movieflix;

import com.movieflix.config.JWTUserData;
import com.movieflix.config.TokenService;
import com.movieflix.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @Test
    void shouldGenerateAndValidateToken() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret-key");
        ReflectionTestUtils.setField(tokenService, "issuer", "movieflix");
        ReflectionTestUtils.setField(tokenService, "expirationSeconds", 3600L);

        User user = User.builder().id(1L).name("Maria").email("maria@email.com").build();

        String token = tokenService.generateToken(user);
        JWTUserData data = tokenService.validateToken(token).orElseThrow();

        assertEquals(1L, data.userId());
        assertEquals("maria@email.com", data.email());
        assertEquals("Maria", data.name());
    }

    @Test
    void shouldRejectInvalidToken() {
        TokenService tokenService = new TokenService();
        ReflectionTestUtils.setField(tokenService, "secret", "test-secret-key");
        ReflectionTestUtils.setField(tokenService, "issuer", "movieflix");
        ReflectionTestUtils.setField(tokenService, "expirationSeconds", 3600L);

        assertTrue(tokenService.validateToken("token.invalido.aqui").isEmpty());
    }
}
