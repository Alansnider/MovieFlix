package com.movieflix.controller;

import com.movieflix.config.TokenService;
import com.movieflix.dto.request.LoginRequest;
import com.movieflix.dto.request.RegisterUserRequest;
import com.movieflix.dto.response.LoginResponse;
import com.movieflix.dto.response.UserResponse;
import com.movieflix.entity.User;
import com.movieflix.exception.UsernameOrPasswordInvalidException;
import com.movieflix.mapper.UserMapper;
import com.movieflix.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Recurso responsável pela autenticação e registro de usuários")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Operation(summary = "Registrar novo usuário")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        User saved = userService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(saved));
    }

    @Operation(summary = "Login (retorna o token JWT)")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken credentials =
                    new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authentication = authenticationManager.authenticate(credentials);
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(new LoginResponse(tokenService.generateToken(user)));
        } catch (BadCredentialsException ex) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválido");
        }
    }
}