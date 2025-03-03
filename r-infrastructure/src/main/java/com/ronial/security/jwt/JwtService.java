package com.ronial.security.jwt;

public interface JwtService {
    String createJwt(JwtPayload payload);
    JwtPayload parseJwt(String jwt);
}
