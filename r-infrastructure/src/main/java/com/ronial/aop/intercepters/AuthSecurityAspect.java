package com.ronial.aop.intercepters;

import com.ronial.aop.annotations.AuthSecurity;
import com.ronial.entities.User;
import com.ronial.exceptions.AuthException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class AuthSecurityAspect {
    private boolean isAnonymousUser(Object object) {
        return object instanceof String;
    }

    @Before("@annotation(authSecurity)")
    public void execute(AuthSecurity authSecurity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated() || isAnonymousUser(authentication.getPrincipal())) {
            throw new AuthException(this.getClass(),
                    AuthException.AuthExceptionMessage.REQUIRE_AUTHENTICATION);
        }
        User user = (User) authentication.getPrincipal();
        if (Arrays.asList(authSecurity.roles()).isEmpty()) return;
        if (!Arrays.asList(authSecurity.roles()).contains(user.getRole())) {
            throw new AuthException(this.getClass(), AuthException.AuthExceptionMessage.UNAUTHORIZED);
        }
    }
}
