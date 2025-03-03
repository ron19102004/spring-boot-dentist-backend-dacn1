package com.ronial.dto.auth;

import com.ronial.entities.Gender;
import jakarta.validation.constraints.NotNull;

public record RegisterRequestDto(
        @NotNull String fullName,
        @NotNull String email,
        @NotNull String password,
        @NotNull String phone,
        @NotNull String username,
        @NotNull Gender gender
) {
}
