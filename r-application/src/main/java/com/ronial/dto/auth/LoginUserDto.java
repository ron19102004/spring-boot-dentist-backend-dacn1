package com.ronial.dto.auth;

import jakarta.validation.constraints.NotNull;

public record LoginUserDto(
        @NotNull String username,
        @NotNull String password
) {
}
