package com.ronial.services;

import com.ronial.entities.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> loadUserFromUsernameLogin(String username);
    User registerUser(User user);
}
