package com.ronial.controllers;

import com.ronial.dto.auth.LoginUserDto;
import com.ronial.dto.auth.RegisterRequestDto;
import com.ronial.entities.User;
import com.ronial.exceptions.AuthException;
import com.ronial.mappers.UserMapper;
import com.ronial.models.ApiResponse;
import com.ronial.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    public AuthController(AuthService authService, DaoAuthenticationProvider daoAuthenticationProvider) {
        this.authService = authService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        User user = authService.registerUser(UserMapper.getUserFromRegisterRequest(registerRequestDto));
        return ResponseEntity.ok(ApiResponse.<User>builder()
                .code(200)
                .data(user)
                .message("success")
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> loginUser(@RequestBody @Valid LoginUserDto loginUserDto) {
        //Check username in redis

        User user = authService.loadUserFromUsernameLogin(loginUserDto.username())
                .orElseThrow(() -> new AuthException(this.getClass(), AuthException.AuthExceptionMessage.UNAUTHORIZED));
        Authentication authentication = daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), loginUserDto.password()));
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthException(this.getClass(), AuthException.AuthExceptionMessage.UNAUTHORIZED);
        }

        return ResponseEntity.ok(ApiResponse.<User>builder()
                .code(200)
                .data(user)
                .message("success")
                .build());
    }
}
