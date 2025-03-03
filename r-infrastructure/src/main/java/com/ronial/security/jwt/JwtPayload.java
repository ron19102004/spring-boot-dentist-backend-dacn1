package com.ronial.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtPayload {
    private String subject;
    private Map<String, Object> claims;
    private long timeLive;
    public JwtPayload(Jwt jwt){
        this.subject = jwt.getSubject();
        this.claims = jwt.getClaims();
        this.timeLive = 0L;
    }
}
