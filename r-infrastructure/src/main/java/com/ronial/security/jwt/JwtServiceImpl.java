package com.ronial.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Autowired
    public JwtServiceImpl(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public String createJwt(JwtPayload payload) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder claimsSetBuilder = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(payload.getTimeLive(), ChronoUnit.MINUTES))
                .subject(payload.getSubject());
        payload.getClaims().forEach(claimsSetBuilder::claim);
        JwtClaimsSet claimsSet = claimsSetBuilder.build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    @Override
    public JwtPayload parseJwt(String jwt) {
        Jwt jwtDecode = jwtDecoder.decode(jwt);
        return new JwtPayload(jwtDecode);
    }
}
