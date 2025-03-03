package com.ronial.mappers;

import com.ronial.dto.auth.RegisterRequestDto;
import com.ronial.entities.Role;
import com.ronial.entities.User;

import java.time.LocalDateTime;

public class UserMapper {
    public static User getUserFromRegisterRequest(RegisterRequestDto dto) {
        return User.builder()
                .id(0L)
                .email(dto.email())
                .fullName(dto.fullName())
                .password(dto.password())
                .role(Role.PATIENT)
                .phone(dto.phone())
                .active(true)
                .gender(dto.gender())
                .username(dto.username())
                .OTPCode("")
                .OTPExpiredAt(LocalDateTime.now())
                .build();
    }
}
