package com.ronial.services.impls;

import com.ronial.entities.User;
import com.ronial.repositories.UserRepository;
import com.ronial.services.AuthService;
import com.ronial.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> loadUserFromUsernameLogin(String username) {
        if (RegexUtils.isValidEmail(username)) {
            return userRepository.findByUsername(username);
        }
        if (RegexUtils.isValidPhone(username)) {
            return userRepository.findByPhone(username);
        }
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
