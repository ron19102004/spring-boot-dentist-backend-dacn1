package com.ronial.exceptions;

import lombok.Getter;

public class AuthException extends InfrastructureException {
    @Getter
    public enum AuthExceptionMessage {
        REQUIRE_AUTHENTICATION("Require authentication"),
        UNAUTHORIZED("Unauthorized"),
        USER_NOT_FOUND("User not found"),
        EMAIL_ALREADY_EXISTS("Email already exists"),
        EMAIL_NOT_EXISTS("Email not exists"),
        PHONE_ALREADY_EXISTS("Phone already exists"),
        PHONE_NOT_EXISTS("Phone not exists"),
        PASSWORD_INCORRECT("Password incorrect");
        private final String message;

        AuthExceptionMessage(String message) {
            this.message = message;
        }

    }

    private final AuthExceptionMessage message;

    public AuthException(Class<?> clazz, AuthExceptionMessage message) {
        super(clazz, message.getMessage());
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }
}
